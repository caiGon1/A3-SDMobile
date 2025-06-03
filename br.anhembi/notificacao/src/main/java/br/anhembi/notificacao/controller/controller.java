package br.anhembi.notificacao.controller;

import br.anhembi.notificacao.dto.dto;
import br.anhembi.notificacao.model.model;
import br.anhembi.notificacao.service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/notificacao")
public class controller {

    @Autowired
    private service service;

    @GetMapping("/find/{userId}")
    public ResponseEntity<List<model>> findById(@PathVariable long userId) {
        List<model> optionalModels = service.findById(userId);
        if(optionalModels.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalModels);
    }

    @PostMapping("/send")
    public ResponseEntity<model> create(@RequestBody model newModel) {
        LocalDate data = LocalDate.now();
        newModel.setData(data);
        if (newModel.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        model newBody = service.create(newModel);
        return ResponseEntity.ok(newBody);
    }
    
    @PostMapping
    public ResponseEntity<dto> receber(@RequestBody dto dto) {
        service.processarDto(dto);
        return ResponseEntity.ok(dto);
    }
}
