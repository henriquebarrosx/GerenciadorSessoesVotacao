package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class GuidelineSessionAlreadyStartedException extends RuntimeException {
    public GuidelineSessionAlreadyStartedException() {
       super("Guideline already started");
    }
}
