package com.example.gerenciador_sessoes_votacao.v1.controllers.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CadastroVotoRequest {
    private String valor;
    private String associadoCpf;
}
