package br.com.cbf.campeonatoBrasileiro.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "jogos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Jogo   {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "time1_id", nullable = false)
    private Time time1;

    @ManyToOne
    @JoinColumn(name = "time2_id", nullable = false)
    private Time time2;
    private int golsTime1;
    private int golsTime2;
    private int publicoPagante;
    private LocalDateTime data;
    private int rodada;
    private boolean encerrado;
}
