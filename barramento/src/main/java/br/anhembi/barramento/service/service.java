package br.anhembi.barramento.service;

import org.springframework.stereotype.Service;

import br.anhembi.barramento.dto.dto;
import br.anhembi.barramento.model.model;
import br.anhembi.barramento.repo.repository;

@Service
public class service {
    private final repository repository;

    public service(repository  repository) {
        this.repository = repository;
    }


     public boolean processarEvento(dto eventoDTO) {
        System.out.println("Recebido evento do user " + eventoDTO.getUserId()
            + ": " + eventoDTO.getMensagem()
            + " em " + eventoDTO.getData());

            if (repository.existsByUserIdAndData(eventoDTO.getUserId(), eventoDTO.getData())) {
            return false; // Evento duplicado
        }

        model evento = new model();
        evento.setUserId(eventoDTO.getUserId());
        evento.setData(eventoDTO.getData());
        evento.setMensagem(eventoDTO.getMensagem());

        repository.save(evento);
        return true;
    }
}
