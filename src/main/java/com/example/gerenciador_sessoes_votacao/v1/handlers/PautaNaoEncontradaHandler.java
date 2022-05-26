package com.example.gerenciador_sessoes_votacao.v1.handlers;

import com.example.gerenciador_sessoes_votacao.v1.exceptions.PautaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PautaNaoEncontradaHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PautaNaoEncontradaException.class)
    public String buscarMensagem(PautaNaoEncontradaException exception) {
        return exception.getMessage();
    }
}
