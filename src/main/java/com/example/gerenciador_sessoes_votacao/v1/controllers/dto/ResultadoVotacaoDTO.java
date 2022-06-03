package com.example.gerenciador_sessoes_votacao.v1.controllers.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoVotacaoDTO {
    private Long totalVotos;
    private Long totalVotosSim;
    private Long totalVotosNao;
    private String tituloPauta;
    private String votoVencedor;
}
