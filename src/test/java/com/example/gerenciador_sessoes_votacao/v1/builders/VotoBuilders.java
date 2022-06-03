package com.example.gerenciador_sessoes_votacao.v1.builders;

import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.ResultadoVotacaoDTO;

public class VotoBuilders {
    public static ResultadoVotacaoDTO obterResultadoVotacao() {
        return ResultadoVotacaoDTO.builder()
                .totalVotos(50L)
                .tituloPauta("BK > MC")
                .totalVotosSim(30L)
                .totalVotosNao(20L)
                .votoVencedor("SIM")
                .build();
    }
}
