package br.com.cbf.campeonatoBrasileiro.repository;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimeRepository extends JpaRepository<Time, UUID> {
}
