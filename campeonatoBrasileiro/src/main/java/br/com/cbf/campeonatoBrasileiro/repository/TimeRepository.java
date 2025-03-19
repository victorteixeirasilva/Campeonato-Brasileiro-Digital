package br.com.cbf.campeonatoBrasileiro.repository;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeRepository extends JpaRepository<UUID, Time> {
}
