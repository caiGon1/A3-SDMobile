package br.anhembi.barramento.repo;
import br.anhembi.barramento.model.model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repository extends JpaRepository<model, Integer> {
    boolean existsByUserIdAndData(int userId, java.time.LocalDate data);
}

