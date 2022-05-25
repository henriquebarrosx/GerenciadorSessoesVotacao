package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Vote;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.constants.VotesEnum;
import com.example.gerenciador_sessoes_votacao.v1.repositories.VoteRepository;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.VotacaoPautaResponse;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.GuidelineNewVoteRequest;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.AssociateAlreadyVotedException;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final GuidelineService guidelineService;

    public void createNewVote(Long guidelineId, GuidelineNewVoteRequest vote) {
        Guideline guideline = guidelineService.findGuideline(guidelineId);

        GuidelineService.validateIfGuidelineIsAbleToReceiveVotes(guideline.getFinishedAt(), guideline.getId());
        validateIfAssociateAlreadyVoted(vote.getAssociateId(), guideline);

        Vote voteSchema = new Vote(VotesEnum.getInstance(vote.getValue()), guideline, vote.getAssociateId());
        voteRepository.save(voteSchema);
    }

    private void validateIfAssociateAlreadyVoted(String associateCpf, Guideline guideline) {
        Optional<Vote> byAssociateCpfAndGuideline = voteRepository.findByAssociateCpfAndGuideline(associateCpf, guideline);

        if (byAssociateCpfAndGuideline.isPresent()) {
            throw new AssociateAlreadyVotedException("Associate " + associateCpf + " already voted");
        }
    }

    public VotacaoPautaResponse buscaResultadoFinalVotacao(Long guidelineId) {
        Guideline guideline = guidelineService.findGuideline(guidelineId);
        List<Vote> votes = voteRepository.findByGuideline(guideline);

        long totalVotosPositivos = buscarTotalVotosPositivos(votes);
        long totalVotosNegativos = buscarTotalVotosNegativos(votes);

        return buscaResultadoVotacao(guideline, totalVotosPositivos, totalVotosNegativos);
    }

    private VotacaoPautaResponse buscaResultadoVotacao(Guideline guideline, Long totalVotosPositivos, Long totalVotosNegativos) {
        return VotacaoPautaResponse.builder()
                .tituloPauta(guideline.getTitle())
                .totalVotosNao(totalVotosNegativos)
                .totalVotosSim(totalVotosPositivos)
                .totalVotos(totalVotosPositivos + totalVotosNegativos)
                .votoVencedor(buscarVotoVencedor(totalVotosPositivos, totalVotosNegativos))
                .build();
    }

    private Long buscarTotalVotosPositivos(List<Vote> votosCadastrados) {
        return votosCadastrados.stream().filter(vote -> vote.getValue().equals(VotesEnum.SIM)).count();
    }

    private Long buscarTotalVotosNegativos(List<Vote> votosCadastrados) {
        return votosCadastrados.stream().filter(vote -> vote.getValue().equals(VotesEnum.NAO)).count();
    }

    private String buscarVotoVencedor(Long totalVotosPositivos, Long totalVotosNegativos) {
        if (totalVotosPositivos.equals(totalVotosNegativos)) {
            return "Empate";
        }

        return totalVotosPositivos > totalVotosNegativos ? VotesEnum.SIM.name() : VotesEnum.NAO.name();
    }
}
