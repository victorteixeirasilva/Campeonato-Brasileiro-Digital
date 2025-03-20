package br.com.cbf.campeonatoBrasileiro.controller;

import br.com.cbf.campeonatoBrasileiro.domain.dto.request.jogo.RequestJogoFinalizadoDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo.ClassificacaoDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo.ClassificacaoTimeDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo.JogoResponseFinalizadoDTO;
import br.com.cbf.campeonatoBrasileiro.domain.entity.Jogo;
import br.com.cbf.campeonatoBrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> obterJogo(@PathVariable int id){
        return ResponseEntity.ok(service.obterJogo(id));
    }

    @PostMapping("/finalizar/{id}")
    public ResponseEntity<JogoResponseFinalizadoDTO> finalizar(@PathVariable int id, @RequestBody RequestJogoFinalizadoDTO requestJogoFinalizadoDTO){
        return ResponseEntity.ok(service.finalizar(id, requestJogoFinalizadoDTO));
    }

    @GetMapping("/classificacao")
    public ResponseEntity<ClassificacaoDTO> classificacao(){
        return ResponseEntity.ok(service.obterClassificacao());
    }

}
