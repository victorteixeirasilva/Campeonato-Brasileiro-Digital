package br.com.cbf.campeonatoBrasileiro.domain.dto.response;

import java.util.UUID;

public record TimeResponseDTO(
        UUID id,
        String nome,
        String sigla,
        String uf
) {
}
