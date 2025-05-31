package br.anhembi.barramento.repo;
import br.anhembi.barramento.model.model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repository extends JpaRepository<model, Long> {
    boolean existsByUserIdAndData(Long userId, java.time.LocalDate data);
}

