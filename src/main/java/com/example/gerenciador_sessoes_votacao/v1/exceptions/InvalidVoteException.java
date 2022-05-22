package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class InvalidVoteException extends RuntimeException {

    public InvalidVoteException() {
        super("Vote should only be voted as 'Sim' or 'Não'");
    }

}
