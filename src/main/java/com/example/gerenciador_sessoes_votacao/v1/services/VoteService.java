package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Vote;
import com.example.gerenciador_sessoes_votacao.v1.entities.Associate;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.constants.VotesEnum;
import com.example.gerenciador_sessoes_votacao.v1.repositories.VoteRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.InvalidVoteException;
import com.example.gerenciador_sessoes_votacao.v1.repositories.AssociateRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.AssociateNotFoundException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineNotFoundException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.AssociateAlreadyVotedException;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final GuidelineService guidelineService;
    private final AssociateRepository associateRepository;

    public void createNewVote(Vote vote) {
        Guideline guideline = Optional.ofNullable(guidelineService.findGuideline(vote.getGuideline().getId()))
                .orElseThrow(() -> new GuidelineNotFoundException("Guideline not found"));

        GuidelineService.validateIfGuidelineIsAbleToReceiveVotes(guideline.getFinishedAt(), guideline.getId());
        validateIfVoteHasValidValue(vote.getValue());
        validateIfAssociateAlreadyVoted(vote.getAssociate().getCpf(), vote.getGuideline().getId());

        Vote voteSchema = new Vote(vote.getValue(), vote.getGuideline(), vote.getAssociate());
        voteRepository.save(voteSchema);
    }

    private void validateIfAssociateAlreadyVoted(String associateId, Long guidelineId) {
        Associate associate = Optional.ofNullable(associateRepository.findById(associateId))
                .get()
                .orElseThrow(() -> new AssociateNotFoundException("Associate not found")); // NotFoundException

        Guideline guideline = guidelineService.findGuideline(guidelineId);
        Optional<Vote> vote = voteRepository.findByAssociate(associate, guideline);

        if (vote.isPresent()) {
            new AssociateAlreadyVotedException("Associate with id " + associateId + " already voted");
        }
    }

    private void validateIfVoteHasValidValue(String vote) {
        Boolean voteDoNotHaveValidValue = Stream.of(VotesEnum.SIM, VotesEnum.NAO)
                .noneMatch(enumValue -> String.valueOf(enumValue).equalsIgnoreCase(vote));

        if (voteDoNotHaveValidValue) {
            throw new InvalidVoteException();
        }
    }

    public Long getGuidelineVotes(Long guidelineId) {
        Guideline guideline = guidelineService.findGuideline(guidelineId);

        guidelineService.validateIfGuidelineIsAbleToReceiveVotes(guideline.getFinishedAt(), guideline.getId());

        return voteRepository.findByGuideline(guideline).stream().count();
    }
}
