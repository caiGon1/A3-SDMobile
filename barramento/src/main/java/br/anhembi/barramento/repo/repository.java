package br.anhembi.barramento.repo;
import br.anhembi.barramento.model.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface repository extends JpaRepository<model, Long> {
    boolean existsByUserIdAndData(Long userId, java.time.LocalDate data);
    boolean existsByNotifIdAndData(Long notifId, java.time.LocalDate data);
    boolean existsByAlertaIdAndData(Long alertaId, java.time.LocalDate data);
    boolean existsByIaIdAndData(Long iaId, java.time.LocalDate data);

    Optional<model> findFirstByUserIdAndMensagemOrderByNotifIdDesc(Long userId, String mensagem);
    boolean existsByNotifIdAndMensagem(Long notifId, String mensagem);



}

