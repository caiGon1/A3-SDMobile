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
import br.anhembi.notificacao.model.model;

@Service
public class service {
    

    private final RestTemplate restTemplate;

    public service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Autowired
    private repo repo;




    public boolean isSuspeita(long userId) {
        List<Double> ultimosValores = repo.findAllExceptLast(userId);
        if (ultimosValores.isEmpty()) return false;
        double media = ultimosValores.stream()
                                     .mapToDouble(Double::doubleValue)
                                     .average()
                                     .orElse(0.0);

        double ultimoValor = repo.findValorByUserId((long) 1, PageRequest.of(0, 1)).stream().findFirst().orElse(null);

        return ultimoValor > media * 1.5;
    
}

@Scheduled(fixedRate = 5000)
public String verificar() {
        boolean suspeita = isSuspeita(1);

        String mensagem;
        if (suspeita) {
            System.out.println("Notificação: Transação anormal (valor acima da média histórica)");
            mensagem = "Notificação: Transação anormal (valor acima da média histórica)";
        } else {
            System.out.println("Transação normal");
            mensagem = "Transação normal";
        }

        EventoDTO evento = new EventoDTO();
        evento.setUserId((long) 1);
        evento.setData(repo.findTopByUserIdOrderByNotifIdDesc( (long) 1).map(model::getData).orElse(null));
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

        return mensagem;
    }
    public model create(model newBody) {
        return repo.save(newBody);
    }
    public List<model> findById(long userId) {
        return repo.findAllByUserId(userId);
    }
}



