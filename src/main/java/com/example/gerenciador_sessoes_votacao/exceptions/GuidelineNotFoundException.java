package com.example.gerenciador_sessoes_votacao.exceptions;

public class GuidelineNotFoundException extends RuntimeException {
    public GuidelineNotFoundException(Long id) {
        super("Guideline with id " + id + " not found");
    }
}
