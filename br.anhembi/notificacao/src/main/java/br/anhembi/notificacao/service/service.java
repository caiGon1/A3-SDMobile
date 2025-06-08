package br.anhembi.notificacao.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.anhembi.notificacao.repo.repo;
import br.anhembi.notificacao.dto.EventoDTO;
import br.anhembi.notificacao.dto.dto;
import br.anhembi.notificacao.dto.emailDto;
import br.anhembi.notificacao.model.model;

@Service
public class service {
    
    String eventos = "http://localhost:8082/api/eventos";
    String alertaEmail = "http://localhost:8081/alertaemail";

    dto dto = new dto();
    private final RestTemplate restTemplate;

    public service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private KafkaTemplate<Long, Boolean> kafkaTemplate;



    @Autowired
    private repo repo;
    private volatile boolean usuarioLogado = false;

    public dto processarDto(dto dto) {
        this.usuarioLogado = (dto.isLogin());
        this.dto.setUserId(dto.getUserId());
        this.dto.setEmail(dto.getEmail());
        System.out.println("Recebido: " + dto.isLogin() + " UserId: " + dto.getUserId() + " Email: " + dto.getEmail());
        
        return this.dto;
    }
    public boolean isUsuarioLogado() {
        return this.usuarioLogado;
    }

    public boolean processarSuspeita(Long userId){
     return isSuspeita(userId);
    }

    public boolean isSuspeita(long userId) {
        List<Double> ultimosValores = repo.findAllExceptLast(dto.getUserId());
        if (ultimosValores.isEmpty()) return false;
        double media = ultimosValores.stream()
                                     .mapToDouble(Double::doubleValue)
                                     .average()
                                     .orElse(0.0);

        double ultimoValor = repo.findValorByUserId(dto.getUserId(), PageRequest.of(0, 1)).stream().findFirst().orElse(null);
       
        Boolean suspeita = ultimoValor >media *1.5;
        kafkaTemplate.send("req.suspeita", userId ,suspeita); 
        return suspeita;

    
}




public void verificar(Boolean suspeita) { 



        String mensagem;
        if (suspeita) {
            System.out.println("Notificação: Transação anormal (valor acima da média histórica)");
            mensagem = "Notificação: Transação anormal (valor acima da média histórica)";

            emailDto emailDto = new emailDto();

            emailDto.setSuspeita(suspeita);
            emailDto.setUserId(dto.getUserId());
            emailDto.setNotifId(repo.findNotifIdByUserId(dto.getUserId()).stream().findFirst().orElse(null));
            emailDto.setEmail(dto.getEmail());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<emailDto> request = new HttpEntity<emailDto>(emailDto, headers);

            try {
                restTemplate.postForEntity(alertaEmail, request, emailDto.class);
                System.out.println("Email enviado ao usuário");
            } catch (Exception e) {
                System.err.println("Erro ao enviar email: " + e.getMessage());
            }
        } else {
            System.out.println("Transação normal");
            mensagem = "Transação normal";
        }

        EventoDTO evento = new EventoDTO();
        evento.setUserId(dto.getUserId());
        evento.setNotifId(repo.findTopByUserIdOrderByNotifIdDesc(dto.getUserId()).map(model::getNotifId).orElse(null));
        evento.setData(repo.findTopByUserIdOrderByNotifIdDesc(dto.getUserId()).map(model::getData).orElse(null));
        evento.setMensagem(mensagem);
        
        //envio para o barramento

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EventoDTO> request = new HttpEntity<EventoDTO>(evento, headers);

        try {
            restTemplate.postForEntity(eventos, request, String.class);
            System.out.println("Evento enviado ao barramento");
        } catch (Exception e) {
            System.err.println("Erro ao enviar evento: " + e.getMessage());
        }


}



//kafka listener
@KafkaListener(topics = "req.suspeita", groupId = "grupo-boolean")
public void verificarTransacoes(@Header(KafkaHeaders.RECEIVED_KEY)Long key, Boolean suspeita) {
    if (isUsuarioLogado()) {
            verificar(suspeita);        
    } else {
        System.out.println("Usuário não está logado, não será verificado." + dto.isLogin() + " UserId: " + dto.getUserId());
    }
}


    public model create(model newBody) {
        model salvo = repo.save(newBody);
        isSuspeita(this.dto.getUserId());
        return salvo;
    }
    public List<model> findById(long userId) {
        return repo.findAllByUserId(userId);
    }

}




