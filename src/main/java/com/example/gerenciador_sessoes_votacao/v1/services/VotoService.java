package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Voto;
import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;
import com.example.gerenciador_sessoes_votacao.v1.constants.EnumVotos;
import com.example.gerenciador_sessoes_votacao.v1.repositories.VotoRepository;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.ResultadoVotacaoResponse;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.CadastroVotoRequest;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.CadastroDeVotosPorAssociadoException;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final PautaService pautaService;
    private final VotoRepository votoRepository;

    public void cadastrarVoto(Long pautaId, CadastroVotoRequest voto) {
        Pauta pauta = pautaService.buscarPauta(pautaId);

        PautaService.validarStatusDaPauta(pauta.getHorarioEncerramento(), pauta.getId());
        validarSeAssociadoPossuiVotoNaPauta(voto.getAssociadoCpf(), pauta);

        Voto corpoVoto = new Voto(EnumVotos.getInstance(voto.getValor()), pauta, voto.getAssociadoCpf());
        votoRepository.save(corpoVoto);
    }

    private void validarSeAssociadoPossuiVotoNaPauta(String associadoCpf, Pauta pauta) {
        Optional<Voto> votoDoAssociadoNestaPauta = votoRepository.buscarVotosPorAssociadoCpfEPauta(associadoCpf, pauta);

        if (votoDoAssociadoNestaPauta.isPresent()) {
            throw new CadastroDeVotosPorAssociadoException("Associado " + associadoCpf + " já possui voto cadastrado nesta pauta");
        }
    }

    public ResultadoVotacaoResponse buscarTotalVotos(Long guidelineId) {
        Pauta pauta = pautaService.buscarPauta(guidelineId);
        List<Voto> votos = votoRepository.buscarVotosPorPauta(pauta);

        long totalVotosPositivos = buscarTotalVotosPositivos(votos);
        long totalVotosNegativos = buscarTotalVotosNegativos(votos);

        return buscaResultadoVotacao(pauta, totalVotosPositivos, totalVotosNegativos);
    }

    private ResultadoVotacaoResponse buscaResultadoVotacao(Pauta pauta, Long totalVotosPositivos, Long totalVotosNegativos) {
        return ResultadoVotacaoResponse.builder()
                .tituloPauta(pauta.getTitulo())
                .totalVotosNao(totalVotosNegativos)
                .totalVotosSim(totalVotosPositivos)
                .totalVotos(totalVotosPositivos + totalVotosNegativos)
                .votoVencedor(buscarVotoVencedor(totalVotosPositivos, totalVotosNegativos))
                .build();
    }

    private Long buscarTotalVotosPositivos(List<Voto> votosCadastrados) {
        return votosCadastrados.stream().filter(voto -> voto.getValor().equals(EnumVotos.SIM)).count();
    }

    private Long buscarTotalVotosNegativos(List<Voto> votosCadastrados) {
        return votosCadastrados.stream().filter(voto -> voto.getValor().equals(EnumVotos.NAO)).count();
    }

    private String buscarVotoVencedor(Long totalVotosPositivos, Long totalVotosNegativos) {
        if (totalVotosPositivos.equals(totalVotosNegativos)) {
            return "Empate";
        }

        return totalVotosPositivos > totalVotosNegativos ? EnumVotos.SIM.name() : EnumVotos.NAO.name();
    }
}
