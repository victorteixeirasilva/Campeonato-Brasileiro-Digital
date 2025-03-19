package br.com.cbf.campeonatoBrasileiro.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "times")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String sigla;
    private String uf;


}
