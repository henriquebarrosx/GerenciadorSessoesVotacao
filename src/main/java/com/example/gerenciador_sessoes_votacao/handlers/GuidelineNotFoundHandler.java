package com.example.gerenciador_sessoes_votacao.handlers;

import com.example.gerenciador_sessoes_votacao.exceptions.GuidelineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GuidelineNotFoundHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GuidelineNotFoundException.class)
    public String guidelineNotFoundHandler(GuidelineNotFoundException exception) {
        return exception.getMessage();
    }
}
