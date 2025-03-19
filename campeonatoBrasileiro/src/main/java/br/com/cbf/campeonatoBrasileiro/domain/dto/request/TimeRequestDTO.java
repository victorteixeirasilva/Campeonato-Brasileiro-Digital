package br.com.cbf.campeonatoBrasileiro.domain.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public record TimeRequestDTO(
        String nome,
        String sigla,
        String uf
) {
}
