package com.example.gerenciador_sessoes_votacao.v1.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.AssociateWithDuplicatedCpfException;

@ControllerAdvice
public class AssociateWithDuplicatedCpfHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AssociateWithDuplicatedCpfException.class)
    public String getMessage(Exception exception) {
        return exception.getMessage();
    }

}
