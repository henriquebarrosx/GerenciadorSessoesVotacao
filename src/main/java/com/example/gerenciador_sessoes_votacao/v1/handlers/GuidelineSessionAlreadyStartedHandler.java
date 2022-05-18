package com.example.gerenciador_sessoes_votacao.v1.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineSessionAlreadyStartedException;

@ControllerAdvice
public class GuidelineSessionAlreadyStartedHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(GuidelineSessionAlreadyStartedException.class)
    String guidelineSessionAlreadyStartedHandler(Exception exception) {
        return exception.getMessage();
    }
}
