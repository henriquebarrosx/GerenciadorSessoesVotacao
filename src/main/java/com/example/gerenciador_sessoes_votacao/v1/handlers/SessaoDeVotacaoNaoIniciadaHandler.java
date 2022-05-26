package com.example.gerenciador_sessoes_votacao.v1.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.SessaoDeVotacaoNaoIniciadaException;

@ControllerAdvice
public class SessaoDeVotacaoNaoIniciadaHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SessaoDeVotacaoNaoIniciadaException.class)
    public String buscarMensagem(Exception exception) {
        return exception.getMessage();
    }
}
