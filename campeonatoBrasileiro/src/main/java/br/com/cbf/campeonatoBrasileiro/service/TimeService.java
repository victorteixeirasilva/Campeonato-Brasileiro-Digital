package br.com.cbf.campeonatoBrasileiro.service;

import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import br.com.cbf.campeonatoBrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public void cadastrarTime(Time time){
        repository.save(time);
    }

    public List<Time> listarTimes() {
        return repository.findAll();
    }

    public Time detalhesDoTime(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));
    }

}
