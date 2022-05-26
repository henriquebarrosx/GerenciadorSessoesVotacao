package com.example.gerenciador_sessoes_votacao.v1.handlers;

import com.example.gerenciador_sessoes_votacao.v1.exceptions.VotoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VotoInvalidoHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VotoInvalidoException.class)
    public String buscarMensagem(Exception exception) {
        return exception.getMessage();
    }

}
