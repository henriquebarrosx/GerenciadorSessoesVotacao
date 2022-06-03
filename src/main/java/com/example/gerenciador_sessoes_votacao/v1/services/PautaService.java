package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.v1.repositories.PautaRepository;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroPautaDTO;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.PautaEncerradaException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.PautaNaoEncontradaException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.SessaoDeVotacaoJaIniciadaException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.SessaoDeVotacaoNaoIniciadaException;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    public List<Pauta> buscarPautas() {
        return pautaRepository.findAll();
    }

    public Pauta buscarPauta(Long pautaId) throws PautaNaoEncontradaException {
        return pautaRepository
                .findById(pautaId)
                .orElseThrow(() -> new PautaNaoEncontradaException("Pauta não encontrada"));
    }

    public Pauta cadastrarPauta(CadastroPautaDTO payload) {
        Pauta pauta = new Pauta(payload.getTitulo(), payload.getDuracaoEmMinutos());

        if (Objects.isNull(pauta.getDuracaoEmMinutos())) {
            long DURACAO_PADRAO_PAUTA = 1L;
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

    static void validarStatusDaPauta(LocalDateTime horarioEncerramento, Long pautaId) {
        if (Objects.isNull(horarioEncerramento)) {
            throw new SessaoDeVotacaoNaoIniciadaException(pautaId);
        }

        if (LocalDateTime.now().isAfter(horarioEncerramento)) {
            throw new PautaEncerradaException(pautaId);
        }
    }
}
