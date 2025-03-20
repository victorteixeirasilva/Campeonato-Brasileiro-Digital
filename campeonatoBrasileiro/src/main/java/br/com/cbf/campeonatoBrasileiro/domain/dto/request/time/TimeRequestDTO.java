package br.com.cbf.campeonatoBrasileiro.domain.dto.request.time;

public record TimeRequestDTO(
        String nome,
        String sigla,
        String uf,
        String estadio
) {
}
