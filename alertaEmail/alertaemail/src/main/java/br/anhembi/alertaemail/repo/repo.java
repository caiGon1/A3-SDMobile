package br.anhembi.alertaemail.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.alertaemail.model.model;

public interface repo extends JpaRepository<model, Long> {
    boolean existsByNotifId(Long notifId);
}
