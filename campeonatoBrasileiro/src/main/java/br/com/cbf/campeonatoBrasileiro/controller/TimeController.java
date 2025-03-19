package br.com.cbf.campeonatoBrasileiro.controller;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import br.com.cbf.campeonatoBrasileiro.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    @Autowired
    private TimeService service;

    @GetMapping
    public ResponseEntity<List<Time>> getTimes() {
        return ResponseEntity.ok(service.listarTimes());
    }

    @GetMapping("/${id}")
    public ResponseEntity<Time> getTime(@PathVariable UUID id){
        return ResponseEntity.ok(service.detalhesDoTime(id));
    }
}
