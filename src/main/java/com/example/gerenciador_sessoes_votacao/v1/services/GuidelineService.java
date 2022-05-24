package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.repositories.GuidelineRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineNotFoundException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineNotStartedYetException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineHasBeenFinishedException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineSessionAlreadyStartedException;

@Service
@RequiredArgsConstructor
public class GuidelineService {

    private final GuidelineRepository guidelineRepository;

    public void createNewGuideline(Guideline payload) {
        Guideline guideline = new Guideline(payload.getTitle(), payload.getDurationInMinutes());

        if (guideline.getDurationInMinutes() == null) {
            Long defaultSessionDurationTime = 1L;
            guideline.setDurationInMinutes(defaultSessionDurationTime);
        }

        guidelineRepository.save(guideline);
    }

    public void startNewSession(Long id) {
        Guideline guideline = findGuideline(id);

        if (Objects.isNull(guideline.getFinishedAt())) {
            guideline.setFinishedAt(LocalDateTime.now().plusMinutes(guideline.getDurationInMinutes()));
            guidelineRepository.save(guideline);
            return;
        }

        throw new GuidelineSessionAlreadyStartedException();
    }

    public List<Guideline> findAllGuidelines() {
        return guidelineRepository.findAll();
    }

    public Guideline findGuideline(Long guidelineId) {
        return guidelineRepository
                .findById(guidelineId)
                .orElseThrow(() -> new GuidelineNotFoundException("Guideline: " + guidelineId + " not found"));
    }

    static void validateIfGuidelineIsAbleToReceiveVotes(LocalDateTime finishedAt, Long guidelineId) {
        if (Objects.isNull(finishedAt)) {
            throw new GuidelineNotStartedYetException(guidelineId);
        }

        if (LocalDateTime.now().isAfter(finishedAt)) {
            throw new GuidelineHasBeenFinishedException(guidelineId);
        }
    }
}
