package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class PautaNaoEncontradaException extends RuntimeException {
    public PautaNaoEncontradaException(String message) {
        super(message);
    }
}
