package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class AssociateAlreadyVotedException extends RuntimeException {
    public AssociateAlreadyVotedException(String message) {
        super(message);
    }
}
