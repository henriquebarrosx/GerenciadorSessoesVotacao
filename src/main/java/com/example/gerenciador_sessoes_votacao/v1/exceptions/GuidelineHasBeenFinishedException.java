package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class GuidelineHasBeenFinishedException extends RuntimeException{
    public GuidelineHasBeenFinishedException(Long guidelineId) {
        super("The Guideline with id " + guidelineId + " has been finished");
    }
}
