package br.anhembi.barramento.repo;
import br.anhembi.barramento.model.model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repository extends JpaRepository<model, Long> {
    boolean existsByNotifIdAndData(Long notifId, java.time.LocalDate data);
    boolean existsByAlertaIdAndData(Long alertaId, java.time.LocalDate data);
    boolean existsByLoginIdAndData(Long loginId, java.time.LocalDate data);
    boolean existsByIaIdAndData(Long iaId, java.time.LocalDate data);
}

