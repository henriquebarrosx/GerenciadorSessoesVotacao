package com.example.gerenciador_sessoes_votacao.v1.controllers;

import com.example.gerenciador_sessoes_votacao.v1.services.PautaService;
import com.example.gerenciador_sessoes_votacao.v1.builders.PautaBuilders;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.Mockito.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest(controllers = PautaController.class)
public class PautaControllerTest {

    @MockBean
    private PautaService pautaService;

    @Autowired
    private PautaController pautaController;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.pautaController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarListaDePautasCadastradas() {
        when(this.pautaService.buscarPautas())
                .thenReturn(PautaBuilders.obterListaDePautas());

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/v1/pautas")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
