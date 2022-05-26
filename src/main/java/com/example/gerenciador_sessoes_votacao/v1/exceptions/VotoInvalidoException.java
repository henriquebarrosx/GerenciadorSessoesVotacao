package com.example.gerenciador_sessoes_votacao.v1.exceptions;

public class VotoInvalidoException extends RuntimeException {

    public VotoInvalidoException() {
        super("Voto só pode ser votado com 'Sim' ou 'Nao'");
    }

}
