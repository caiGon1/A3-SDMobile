package br.anhembi.notificacao.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.anhembi.notificacao.model.model;

public interface repo extends JpaRepository<model, Long> {

    @Query("SELECT t.valorSaida FROM model t WHERE t.userId = :userId AND t.notifId < (SELECT MAX(t2.notifId) FROM model t2 WHERE t2.userId = :userId) ORDER BY t.data DESC")
    List<Double> findAllExceptLast(@Param("userId") Long userId);


    @Query("SELECT p.valorSaida FROM model p WHERE p.userId = :userId ORDER BY p.notifId DESC")
    List<Double> findValorByUserId(@Param("userId") Long userId, Pageable pageable);

    Optional<model> findTopByUserIdOrderByNotifIdDesc(Long userId);

    List<model> findAllByUserId(Long userId);





}


   

