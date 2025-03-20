package br.com.cbf.campeonatoBrasileiro.domain.dto.response.jogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClassificacaoDTO {

    private List<ClassificacaoTimeDTO> times = new ArrayList<>();
}
