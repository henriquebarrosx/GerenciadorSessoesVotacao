package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class GuidelineNotFoundException extends RuntimeException {
    public GuidelineNotFoundException(String message) {
        super(message);
    }
}
