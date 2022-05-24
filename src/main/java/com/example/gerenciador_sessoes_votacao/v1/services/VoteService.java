package com.example.gerenciador_sessoes_votacao.v1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Vote;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.constants.VotesEnum;
import com.example.gerenciador_sessoes_votacao.v1.repositories.VoteRepository;
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
        voteRepository.findByAssociateCpfAndGuideline(associateCpf, guideline)
                .ifPresent(vote -> {
                    throw new AssociateAlreadyVotedException("Associate " + associateCpf + " already voted");
                });
    }

    public Long getGuidelineVotes(Long guidelineId) {
        Guideline guideline = guidelineService.findGuideline(guidelineId);
        GuidelineService.validateIfGuidelineIsAbleToReceiveVotes(guideline.getFinishedAt(), guideline.getId());

        return voteRepository.findByGuideline(guideline).stream().count();
    }
}
