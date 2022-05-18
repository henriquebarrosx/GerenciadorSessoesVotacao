package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class GuidelineNotFoundException extends RuntimeException {
    public GuidelineNotFoundException(Long id) {
        super("Guideline with id " + id + " not found");
    }
}
