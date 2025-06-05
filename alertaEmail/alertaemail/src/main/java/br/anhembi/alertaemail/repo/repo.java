package br.anhembi.alertaemail.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.alertaemail.model.model;

public interface repo extends JpaRepository<model, Long> {
    boolean existsByNotifId(Long notifId);
    Optional<model> findTopByUserIdOrderByAlertaIdDesc(Long userId);

}
