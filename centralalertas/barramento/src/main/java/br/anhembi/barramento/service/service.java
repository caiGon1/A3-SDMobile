package br.anhembi.barramento.service;

import org.springframework.stereotype.Service;
import config;
import br.anhembi.barramento.dto.dto;
import br.anhembi.barramento.model.model;
import br.anhembi.barramento.repo.repository;

@Service


public class service {
    private final RestTemplate restTemplate;

public service (RestTemplate restTemplate)

{

this.restTemplate = restTemplate;

}
    private final repository repository;
    

    public service(repository  repository) {
        this.repository = repository;
    }


     public boolean processarEvento(dto eventoDTO) {
        System.out.println("Recebido evento do user " + eventoDTO.getUserId()
            + ": " + eventoDTO.getMensagem()
            + " em " + eventoDTO.getData());

        if (
            repository.existsByNotifIdAndData(eventoDTO.getNotifId(), eventoDTO.getData()) &&
            repository.existsByAlertaIdAndData(eventoDTO.getAlertaId(), eventoDTO.getData()) &&
            repository.existsByLoginIdAndData(eventoDTO.getLoginId(), eventoDTO.getData()) &&
            repository.existsByIaIdAndData(eventoDTO.getIaId(), eventoDTO.getData())
        ){
            return false; // Evento duplicado
        }else{

        model evento = new model();
        evento.setUserId((long) eventoDTO.getUserId());
        evento.setData(eventoDTO.getData());
        evento.setMensagem(eventoDTO.getMensagem());
        evento.setNotifId(eventoDTO.getNotifId());
        evento.setAlertaId(eventoDTO.getAlertaId());
        evento.setIaId(eventoDTO.getIaId());
        evento.setLoginId(eventoDTO.getLoginId());
        evento.setEmail(eventoDTO.getEmail());
        evento.setSenha(eventoDTO.getSenha());


        repository.save(evento);
        return true;
        }
    }
}
