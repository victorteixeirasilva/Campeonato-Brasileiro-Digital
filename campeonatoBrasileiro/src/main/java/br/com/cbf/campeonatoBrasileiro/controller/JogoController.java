package br.com.cbf.campeonatoBrasileiro.controller;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Jogo;
import br.com.cbf.campeonatoBrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/jogo")
public class JogoController {

    @Autowired
    private JogoService service;

    @PostMapping("gerar-jogos")
    public ResponseEntity gerarJogos(){
        service.gerarJogos(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> obterJogos(){
        return ResponseEntity.ok(service.obterJogos());
    }

}
