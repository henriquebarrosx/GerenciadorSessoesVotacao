package com.example.gerenciador_sessoes_votacao.v1.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.v1.services.VotoService;
import com.example.gerenciador_sessoes_votacao.v1.services.PautaService;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroVotoRequest;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.ResultadoVotacaoResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pautas")
public class PautaController {

    private final VotoService votoService;
    private final PautaService pautaService;

    @GetMapping()
    public List<Pauta> buscarPautas() {
        return pautaService.buscarPautas();
    }

    @PostMapping()
    public void cadastrarPauta(@RequestBody Pauta pauta) {
        pautaService.cadastrarPauta(pauta);
    }

    @GetMapping("{id}")
    public Pauta buscarPauta(@PathVariable(value = "id") Long pautaId) {
        return pautaService.buscarPauta(pautaId);
    }

    @GetMapping("/{id}/iniciar")
    public void iniciarSessaoVotacao(@PathVariable(value = "id") Long pautaId) {
        pautaService.iniciarSessaoVotacao(pautaId);
    }

    @GetMapping("/{id}/votos")
    public ResultadoVotacaoResponse buscarTotalVotos(@PathVariable(value = "id") Long pautaId) {
        return votoService.buscarTotalVotos(pautaId);
    }

    @PostMapping("/{id}/votos")
    public void cadastrarVoto(@PathVariable(value = "id") Long pautaId, @RequestBody CadastroVotoRequest cadastroVotoRequest) {
        votoService.cadastrarVoto(pautaId, cadastroVotoRequest);
    }
}