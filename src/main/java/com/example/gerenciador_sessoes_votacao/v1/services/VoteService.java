package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.example.gerenciador_sessoes_votacao.v1.entities.Associate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Vote;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.constants.VotesEnum;
import com.example.gerenciador_sessoes_votacao.v1.repositories.VoteRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.InvalidVoteException;
import com.example.gerenciador_sessoes_votacao.v1.repositories.AssociateRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.AssociateNotFoundException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.AssociateAlreadyVotedException;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final GuidelineService guidelineService;
    private final AssociateRepository associateRepository;

    public void createNewVote(Vote vote) {
        Guideline guideline = guidelineService.findGuideline(vote.getGuideline().getId());

        GuidelineService.validateIfGuidelineIsAbleToReceiveVotes(guideline.getFinishedAt(), guideline.getId());
        validateIfVoteHasValidValue(vote.getValue());
        validateIfAssociateAlreadyVoted(vote.getAssociate().getCpf(), vote.getGuideline().getId());

        Vote voteSchema = new Vote(vote.getValue(), vote.getGuideline(), vote.getAssociate());
        voteRepository.save(voteSchema);
    }

    private void validateIfAssociateAlreadyVoted(String associateId, Long guidelineId) {
        Associate associate = Optional.of(associateRepository.findById(associateId))
                .get()
                .orElseThrow(() -> new AssociateNotFoundException("Associate not found"));

        Guideline guideline = guidelineService.findGuideline(guidelineId);
        Optional<List<Vote>> votes1 = voteRepository.findByGuideline(guideline);
        Optional<List<Vote>> votes2 = voteRepository.findByAssociate(associate);

        if (votes.isPresent()) {
            throw new AssociateAlreadyVotedException("Associate with id " + associateId + " already voted");
        }
    }

    private void validateIfVoteHasValidValue(String vote) {
        boolean voteDoNotHaveValidValue = Stream.of(VotesEnum.SIM, VotesEnum.NAO)
                .noneMatch(enumValue -> String.valueOf(enumValue).equalsIgnoreCase(vote));

        if (voteDoNotHaveValidValue) {
            throw new InvalidVoteException();
        }
    }

    public Long getGuidelineVotes(Long guidelineId) {
        Guideline guideline = guidelineService.findGuideline(guidelineId);
        GuidelineService.validateIfGuidelineIsAbleToReceiveVotes(guideline.getFinishedAt(), guideline.getId());

        return voteRepository.findByGuideline(guideline).stream().count();
    }
}
