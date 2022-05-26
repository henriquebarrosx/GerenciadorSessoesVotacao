package com.example.gerenciador_sessoes_votacao.v1.controllers.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultadoVotacaoResponse {
    private Long totalVotos;
    private Long totalVotosSim;
    private Long totalVotosNao;
    private String tituloPauta;
    private String votoVencedor;
}
