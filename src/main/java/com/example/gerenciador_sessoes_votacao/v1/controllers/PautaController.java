package com.example.gerenciador_sessoes_votacao.v1.controllers;

import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.v1.services.VotoService;
import com.example.gerenciador_sessoes_votacao.v1.services.PautaService;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroVotoDTO;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroPautaDTO;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.ResultadoVotacaoDTO;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pautas")
public class PautaController {

    private final VotoService votoService;
    private final PautaService pautaService;

    @GetMapping
    public ResponseEntity<List<Pauta>> buscarPautas() {
        return new ResponseEntity<>(pautaService.buscarPautas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody CadastroPautaDTO pauta) {
        return new ResponseEntity<>(pautaService.cadastrarPauta(pauta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> buscarPauta(@PathVariable(value = "id") Long pautaId) {
        return new ResponseEntity<>(pautaService.buscarPauta(pautaId), HttpStatus.OK);
    }

    @GetMapping("/{id}/iniciar")
    public ResponseEntity<Void> iniciarSessaoVotacao(@PathVariable(value = "id") Long pautaId) {
        pautaService.iniciarSessaoVotacao(pautaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/votos")
    public ResponseEntity<ResultadoVotacaoDTO> buscarTotalVotos(@PathVariable(value = "id") Long pautaId) {
        return new ResponseEntity<>(votoService.buscarTotalVotos(pautaId), HttpStatus.OK);
    }

    @PostMapping("/{id}/votos")
    public ResponseEntity<Void> cadastrarVoto(@PathVariable(value = "id") Long pautaId, @RequestBody CadastroVotoDTO cadastroVotoDTO) {
        votoService.cadastrarVoto(pautaId, cadastroVotoDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}