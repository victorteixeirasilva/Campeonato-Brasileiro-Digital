package br.com.cbf.campeonatoBrasileiro.service;

import br.com.cbf.campeonatoBrasileiro.domain.dto.request.jogo.RequestJogoFinalizadoDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo.ClassificacaoDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo.ClassificacaoTimeDTO;
import br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo.JogoResponseFinalizadoDTO;
import br.com.cbf.campeonatoBrasileiro.domain.entity.Jogo;
import br.com.cbf.campeonatoBrasileiro.domain.entity.Time;
import br.com.cbf.campeonatoBrasileiro.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;
    
    @Autowired
    private TimeService timeService;

    /**
     * @param primeiraRodada Data da primeira rodada
     */
    public void gerarJogos(LocalDateTime primeiraRodada) {
        final List<Time> times = timeService.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);//.subList(0, times.size()/2));
        all2.addAll(times);//.subList(all1.size(), times.size()));

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();
        int m = times.size() / 2;
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 0;
        for (int i = 0; i < t - 1; i++) {
            rodada = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Time time1;
                Time time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = times.get(t - j - 1);
                    time2 = times.get(j);
                } else {
                    time1 = times.get(j);
                    time2 = times.get(t - j - 1);
                }
                if (time1 == null) {
                    System.out.println("Time  1 null");
                }
                jogos.add(gerarJogo(dataJogo, rodada, time1, time2));
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro no lugar
            times.add(1, times.remove(times.size() - 1));
        }

        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo -> {
            Time time1 = jogo.getTime2();
            Time time2 = jogo.getTime1();
            jogos2.add(gerarJogo(jogo.getData().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
        });
        jogoRepository.saveAll(jogos2);
    }

    private Jogo gerarJogo(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
        Jogo jogo = new Jogo();
        jogo.setTime1(time1);
        jogo.setTime2(time2);
        jogo.setRodada(rodada);
        jogo.setData(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTime1(0);
        jogo.setGolsTime2(0);
        jogo.setPublicoPagante(0);
        return jogo;
    }

    public List<Jogo> obterJogos(){
        return jogoRepository.findAll();
    }

    public Jogo obterJogo(int id) {
        return jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado!"));
    }

    public JogoResponseFinalizadoDTO finalizar(int id, RequestJogoFinalizadoDTO requestJogoFinalizadoDTO) {
        Jogo jogo = jogoRepository.findById(id).orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        if (!jogo.isEncerrado()){
            jogo.setPublicoPagante(requestJogoFinalizadoDTO.publicoPagante());
            jogo.setGolsTime1(requestJogoFinalizadoDTO.golsTime1());
            jogo.setGolsTime2(requestJogoFinalizadoDTO.golsTime2());
            jogo.setEncerrado(true);
            jogoRepository.save(jogo);
        } else {
            throw new RuntimeException("Jogo já foi finalizado!");
        }

        return new JogoResponseFinalizadoDTO(
                "Jogo sendo finalizado! (" +
                        jogo.getTime1().getSigla() +
                        " Gols: " +
                        jogo.getGolsTime1() +
                        " VS " +
                        jogo.getTime2().getSigla() +
                        " Gols: " +
                        jogo.getGolsTime2() +
                        ")", jogo.getId(), jogo.isEncerrado());

    }

    public ClassificacaoDTO obterClassificacao() {
        // (qtd vitorias * 3) + qtdeEmpates
        ClassificacaoDTO classificacaoDTO = new ClassificacaoDTO();
        final List<Time> times = timeService.findAll();
        times.forEach(time -> {
            List<Jogo> jogosMandante = jogoRepository.findByTime1AndEncerrado(time, true);
            List<Jogo> jogosVisitante = jogoRepository.findByTime2AndEncerrado(time, true);
            AtomicInteger vitorias = new AtomicInteger();
            AtomicInteger empates = new AtomicInteger();
            AtomicInteger derrotas = new AtomicInteger();
            AtomicInteger golsSofridos = new AtomicInteger();
            AtomicInteger golsMarcados = new AtomicInteger();

            jogosMandante.forEach(jogo -> {
                if (jogo.getGolsTime1() > jogo.getGolsTime2()) {
                    vitorias.getAndIncrement();
                } else if (jogo.getGolsTime1() < jogo.getGolsTime2()) {
                    derrotas.getAndIncrement();
                } else {
                    empates.getAndIncrement();
                }
                golsMarcados.set(jogo.getGolsTime1());
                golsSofridos.set(jogo.getGolsTime2());
            });
            jogosVisitante.forEach(jogo -> {
                if (jogo.getGolsTime2() > jogo.getGolsTime1()) {
                    vitorias.getAndIncrement();
                } else if (jogo.getGolsTime2() < jogo.getGolsTime1()) {
                    derrotas.getAndIncrement();
                } else {
                    empates.getAndIncrement();
                }
                golsMarcados.set(jogo.getGolsTime2());
                golsSofridos.set(jogo.getGolsTime1());
            });

            ClassificacaoTimeDTO classificacaoTimeDTO = new ClassificacaoTimeDTO();
            classificacaoTimeDTO.setTime(time.getNome());
            classificacaoTimeDTO.setIdTime(time.getId());
            classificacaoTimeDTO.setPontos((vitorias.get() * 3) + empates.get());
            classificacaoTimeDTO.setDerrotas(derrotas.get());
            classificacaoTimeDTO.setEmpates(empates.get());
            classificacaoTimeDTO.setVitorias(vitorias.get());
            classificacaoTimeDTO.setGolsMarcados(golsMarcados.get());
            classificacaoTimeDTO.setGolsSofridos(golsSofridos.get());
            classificacaoTimeDTO.setJogos(derrotas.get() + empates.get() + vitorias.get());

            classificacaoDTO.getTimes().add(classificacaoTimeDTO);

        });

        Collections.sort(classificacaoDTO.getTimes(), Collections.reverseOrder());
        int posicao = 1;
        for (ClassificacaoTimeDTO time : classificacaoDTO.getTimes()) {
            time.setPosicao(posicao++);
        }

        return classificacaoDTO;

    }
}
