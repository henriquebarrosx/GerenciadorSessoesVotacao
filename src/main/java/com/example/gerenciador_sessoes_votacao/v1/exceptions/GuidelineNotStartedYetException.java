package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class GuidelineNotStartedYetException extends RuntimeException {
    public GuidelineNotStartedYetException(Long guidelineId) {
        super("The guideline with id " + guidelineId + " was not started yet");
    }
}
