package com.example.gerenciador_sessoes_votacao.v1.controllers.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPautaDTO {
    private String titulo;
    private Long duracaoEmMinutos;
}
