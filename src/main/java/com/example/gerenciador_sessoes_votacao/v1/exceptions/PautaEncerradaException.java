package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class PautaEncerradaException extends RuntimeException{
    public PautaEncerradaException(Long guidelineId) {
        super("Sessao de votação da pauta " + guidelineId + " já foi encerrada!");
    }
}
