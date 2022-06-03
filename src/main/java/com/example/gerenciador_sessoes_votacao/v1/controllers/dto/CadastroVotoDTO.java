package com.example.gerenciador_sessoes_votacao.v1.controllers.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroVotoDTO {
    private String valor;
    private String associadoCpf;
}
