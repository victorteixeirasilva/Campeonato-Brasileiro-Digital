package br.com.cbf.campeonatoBrasileiro.service;

import br.com.cbf.campeonatoBrasileiro.domain.dto.request.TimeRequestDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.TimeResponseOkDTO;
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

    public TimeResponseOkDTO cadastrarTime(TimeRequestDTO timeRequestDTO){
        Time time = requestToTime(timeRequestDTO);
        repository.save(time);
        return toResposeDTO(time);
    }

    private TimeResponseOkDTO toResposeDTO(Time time) {
        TimeResponseOkDTO timeResponseDTO = new TimeResponseOkDTO(
                time.getId(),
                time.getNome(),
                time.getSigla(),
                time.getUf(),
                time.getEstadio()
        );
        return timeResponseDTO;
    }

    private Time requestToTime(TimeRequestDTO timeRequestDTO) {
        Time time = new Time();

        time.setNome(timeRequestDTO.nome());
        time.setSigla(timeRequestDTO.sigla());
        time.setUf(timeRequestDTO.uf());
        time.setEstadio(timeRequestDTO.estadio());

        return time;
    }

    public List<Time> listarTimes() {
        return repository.findAll();
    }

    public Time detalhesDoTime(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));
    }

    public List<Time> findAll() {
        return repository.findAll();
    }
}
