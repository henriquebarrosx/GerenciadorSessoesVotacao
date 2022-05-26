package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class SessaoDeVotacaoJaIniciadaException extends RuntimeException {
    public SessaoDeVotacaoJaIniciadaException() {
       super("A sessao de votação já foi iniciada");
    }
}
