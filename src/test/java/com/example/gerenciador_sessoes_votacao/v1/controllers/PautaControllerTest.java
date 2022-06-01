package com.example.gerenciador_sessoes_votacao.v1.controllers;

import static org.mockito.Mockito.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static com.example.gerenciador_sessoes_votacao.v1.builders.PautaBuilders.*;

import com.example.gerenciador_sessoes_votacao.v1.services.VotoService;
import com.example.gerenciador_sessoes_votacao.v1.services.PautaService;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(controllers = PautaController.class)
public class PautaControllerTest {

    @MockBean private VotoService votoService;
    @MockBean private PautaService pautaService;
    @Autowired private PautaController pautaController;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.pautaController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarListaDePautasCadastradas() {
        when(this.pautaService.buscarPautas())
                .thenReturn(obterListaDePautas());

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/v1/pautas", obterListaDePautas())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoCadastrarPauta() {
        when(this.pautaService.cadastrarPauta(obterUmaPautaParaCadastro()))
                .thenReturn(obterUmaPauta());

        given()
                .contentType(ContentType.JSON)
                .body(obterUmaPautaParaCadastro())
                .when()
                .post("/api/v1/pautas", obterUmaPauta())
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
