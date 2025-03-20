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
public class ClassificacaoTimeDTO implements Comparable<ClassificacaoTimeDTO>{
    private String time;
    private UUID idTime;
    private int posicao;
    private Integer pontos;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    @Override
    public int compareTo(ClassificacaoTimeDTO o) {
        return this.getPontos().compareTo(o.getPontos());
    }
}
