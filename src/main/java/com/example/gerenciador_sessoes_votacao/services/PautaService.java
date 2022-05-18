package com.example.gerenciador_sessoes_votacao.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.repositories.PautaRepository;

@Service
@RequiredArgsConstructor
public class PautaService {
    private final PautaRepository pautaRepository;

    public List<Pauta> buscarPautas() {
        return pautaRepository.findAll();
    }
}
