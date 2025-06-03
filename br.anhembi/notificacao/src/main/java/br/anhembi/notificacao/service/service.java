package br.anhembi.notificacao.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.anhembi.notificacao.repo.repo;
import br.anhembi.notificacao.dto.EventoDTO;
import br.anhembi.notificacao.dto.dto;
import br.anhembi.notificacao.model.model;

@Service
public class service {
    
    dto dto = new dto();
    private final RestTemplate restTemplate;

    public service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Autowired
    private repo repo;


    public dto processarDto(dto dto) {
        this.dto.setLogin(dto.isLogin());
        this.dto.setUserId(dto.getUserId());
        System.out.println("Recebido: " + dto.isLogin() + " UserId: " + dto.getUserId());
        return this.dto;
    }

    public boolean isSuspeita(long userId) {
        List<Double> ultimosValores = repo.findAllExceptLast(dto.getUserId());
        if (ultimosValores.isEmpty()) return false;
        double media = ultimosValores.stream()
                                     .mapToDouble(Double::doubleValue)
                                     .average()
                                     .orElse(0.0);

        double ultimoValor = repo.findValorByUserId(dto.getUserId(), PageRequest.of(0, 1)).stream().findFirst().orElse(null);

        return ultimoValor > media * 1.5;
    
}



public void verificar() {


        boolean suspeita = isSuspeita(dto.getUserId());
        String mensagem;
        if (suspeita) {
            System.out.println("Notificação: Transação anormal (valor acima da média histórica)");
            mensagem = "Notificação: Transação anormal (valor acima da média histórica)";
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
            restTemplate.postForEntity("http://localhost:8082/api/eventos", request, String.class);
            System.out.println("📤 Evento enviado ao barramento");
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar evento: " + e.getMessage());
        }

    }



@Scheduled(fixedRate = 5000)
public void verificarTransacoes() {
    boolean login = dto.isLogin();
    if (login) {
        verificar();
    } else {
        System.out.println("Usuário não está logado, não será verificado." + dto.isLogin() + " UserId: " + dto.getUserId());
    }
}

    public model create(model newBody) {
        return repo.save(newBody);
    }
    public List<model> findById(long userId) {
        return repo.findAllByUserId(userId);
    }

}




