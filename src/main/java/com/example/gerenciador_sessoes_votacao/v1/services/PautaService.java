package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;

import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroPautaDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.v1.repositories.PautaRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.PautaEncerradaException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.PautaNaoEncontradaException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.SessaoDeVotacaoJaIniciadaException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.SessaoDeVotacaoNaoIniciadaException;

@RequiredArgsConstructor
@Service
public class PautaService {

    private final Long DURACAO_PADRAO_PAUTA = 1L;
    private final PautaRepository pautaRepository;

    public List<Pauta> buscarPautas() {
        return pautaRepository.findAll();
    }

    public Pauta cadastrarPauta(CadastroPautaDTO payload) {
        Pauta pauta = new Pauta(payload.getTitulo(), payload.getDuracaoEmMinutos());

        if (Objects.isNull(pauta.getDuracaoEmMinutos())) {
            pauta.setDuracaoEmMinutos(DURACAO_PADRAO_PAUTA);
        }

        pautaRepository.save(pauta);
        return pauta;
    }

    public void iniciarSessaoVotacao(Long id) {
        Pauta pauta = buscarPauta(id);

        if (Objects.isNull(pauta.getHorarioEncerramento())) {
            pauta.setHorarioEncerramento(LocalDateTime.now().plusMinutes(pauta.getDuracaoEmMinutos()));
            pautaRepository.save(pauta);
            return;
        }

        throw new SessaoDeVotacaoJaIniciadaException();
    }

    public Pauta buscarPauta(Long pautaId) {
        return pautaRepository
                .findById(pautaId)
                .orElseThrow(() -> new PautaNaoEncontradaException("Pauta: " + pautaId + " não encontrada"));
    }

    static void validarStatusDaPauta(LocalDateTime horarioEncerramento, Long pautaId) {
        if (Objects.isNull(horarioEncerramento)) {
            throw new SessaoDeVotacaoNaoIniciadaException(pautaId);
        }

        if (LocalDateTime.now().isAfter(horarioEncerramento)) {
            throw new PautaEncerradaException(pautaId);
        }
    }
}
