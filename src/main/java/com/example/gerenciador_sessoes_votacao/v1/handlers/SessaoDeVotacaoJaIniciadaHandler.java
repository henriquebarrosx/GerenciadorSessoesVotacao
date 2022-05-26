package com.example.gerenciador_sessoes_votacao.v1.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.SessaoDeVotacaoJaIniciadaException;

@ControllerAdvice
public class SessaoDeVotacaoJaIniciadaHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SessaoDeVotacaoJaIniciadaException.class)
    String buscarMensagem(Exception exception) {
        return exception.getMessage();
    }
}
