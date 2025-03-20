package br.com.cbf.campeonatoBrasileiro.repository;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Jogo;
import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    List<Jogo> findByTime1AndEncerrado(Time time, boolean b);

    List<Jogo> findByTime2AndEncerrado(Time time, boolean b);
}
