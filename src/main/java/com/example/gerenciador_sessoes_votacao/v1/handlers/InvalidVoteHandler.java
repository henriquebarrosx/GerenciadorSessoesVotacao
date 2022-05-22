package com.example.gerenciador_sessoes_votacao.v1.handlers;

import com.example.gerenciador_sessoes_votacao.v1.exceptions.InvalidVoteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidVoteHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidVoteException.class)
    public String getMessage(Exception exception) {
        return exception.getMessage();
    }

}
