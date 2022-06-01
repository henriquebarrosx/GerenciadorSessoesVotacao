package com.example.gerenciador_sessoes_votacao.v1.builders;

import java.util.List;
import java.time.LocalDateTime;

import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroPautaDTO;

public class PautaBuilders {

    public static List<Pauta> obterListaDePautas() {
        return List.of(obterUmaPauta());
    }

    public static Pauta obterUmaPauta() {
        return Pauta.builder()
                .id(1L)
                .titulo("BK > MC")
                .duracaoEmMinutos(10L)
                .horarioEncerramento(LocalDateTime.now().plusMinutes(10L))
                .build();
    }

    public static Pauta obterUmaPautaComSessaoDeVotoNaoIniciada() {
        return Pauta.builder()
                .id(1L)
                .titulo("BK > MC")
                .duracaoEmMinutos(10L)
                .build();
    }

    public static CadastroPautaDTO obterUmaPautaParaCadastro() {
        return CadastroPautaDTO.builder()
                .titulo("BK > MC")
                .duracaoEmMinutos(10L)
                .build();
    }
}
