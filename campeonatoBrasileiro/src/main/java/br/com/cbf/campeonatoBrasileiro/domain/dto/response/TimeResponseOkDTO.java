package br.com.cbf.campeonatoBrasileiro.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimeResponseOkDTO {
    private final String MENSAGEM = "Time cadastrado corretamente!";
    private UUID id;
    private String nome;
    private String sigla;
    private String uf;
}
