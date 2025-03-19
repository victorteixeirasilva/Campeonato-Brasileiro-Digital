package br.com.cbf.campeonatoBrasileiro.repository;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JogoRepository extends JpaRepository<UUID, Jogo> {
}
