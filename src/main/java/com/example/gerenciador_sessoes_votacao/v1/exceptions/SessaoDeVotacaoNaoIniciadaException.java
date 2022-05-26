package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class SessaoDeVotacaoNaoIniciadaException extends RuntimeException {
    public SessaoDeVotacaoNaoIniciadaException(Long pautaId) {
        super("Sessao de votação da pauta " + pautaId + " ainda não foi iniciada!");
    }
}
