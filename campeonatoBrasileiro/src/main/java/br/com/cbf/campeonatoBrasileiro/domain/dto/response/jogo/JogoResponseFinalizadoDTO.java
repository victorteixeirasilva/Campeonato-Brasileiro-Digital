package br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JogoResponseFinalizadoDTO {

    private String mensagem;
    private UUID idJogo;
    private boolean finalizado;

}
