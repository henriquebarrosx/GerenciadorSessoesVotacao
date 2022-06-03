package com.example.gerenciador_sessoes_votacao.v1.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static com.example.gerenciador_sessoes_votacao.v1.builders.PautaBuilders.*;

import com.example.gerenciador_sessoes_votacao.v1.services.VotoService;
import com.example.gerenciador_sessoes_votacao.v1.services.PautaService;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.ResultadoVotacaoDTO;
import static com.example.gerenciador_sessoes_votacao.v1.builders.VotoBuilders.obterResultadoVotacao;

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
                .status(HttpStatus.OK);
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
                .status(HttpStatus.CREATED);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarPautaPorId() {
        when(this.pautaService.buscarPauta(anyLong()))
                .thenReturn(obterUmaPauta());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/pautas", obterUmaPauta())
                .then()
                .status(HttpStatus.OK);
    }

    @Test
    public void deveRetornarSucesso_QuandoIniciarSessaoDeVotacaoDaPauta() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/pautas/{id}/iniciar", 1L)
                .then()
                .status(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarTotalDeVotosDeUmaSessaoDeVotacao() {
        when(this.votoService.buscarTotalVotos(1L))
                .thenReturn(obterResultadoVotacao());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/pautas/{id}/votos", 1L)
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .extract()
                .as(ResultadoVotacaoDTO.class);
    }
}
