package br.com.cbf.campeonatoBrasileiro.controller;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Jogo;
import br.com.cbf.campeonatoBrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> obterJogo(@PathVariable UUID id){
        return ResponseEntity.ok(service.obterJogo(id));
    }

}
