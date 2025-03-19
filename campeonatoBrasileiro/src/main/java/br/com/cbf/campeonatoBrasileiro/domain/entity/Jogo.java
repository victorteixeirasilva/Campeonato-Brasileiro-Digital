package br.com.cbf.campeonatoBrasileiro.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "jogos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Jogo   {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Time time1;
    private Time time2;
    private int golsTime1;
    private int golsTime2;
    private int publicoPagante;
}
