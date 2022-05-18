package com.example.gerenciador_sessoes_votacao.controllers;

import java.util.List;
import com.example.gerenciador_sessoes_votacao.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.services.PautaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SessionController {
    private final PautaService pautaService;

    @GetMapping("/pautas")
    public List<Pauta> buscarPautas() {
        return pautaService.buscarPautas();
    }
}
