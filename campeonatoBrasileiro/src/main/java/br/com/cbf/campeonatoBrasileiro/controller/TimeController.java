package br.com.cbf.campeonatoBrasileiro.controller;

import br.com.cbf.campeonatoBrasileiro.domain.dto.request.time.TimeRequestDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.time.TimeResponseOkDTO;
import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import br.com.cbf.campeonatoBrasileiro.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Time> getTime(@PathVariable UUID id){
        return ResponseEntity.ok(service.detalhesDoTime(id));
    }

    @PostMapping
    public ResponseEntity<TimeResponseOkDTO> addTime(@RequestBody TimeRequestDTO time){
        return ResponseEntity.ok(service.cadastrarTime(time));
    }
}
