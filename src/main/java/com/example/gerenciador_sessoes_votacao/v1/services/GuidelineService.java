package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.repositories.GuidelineRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineNotFoundException;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineSessionAlreadyStartedException;

@Service
@RequiredArgsConstructor
public class GuidelineService {
    private final GuidelineRepository guidelineRepository;

    public Guideline create(Guideline payload) {
        Guideline guideline = new Guideline(payload.getTitle(), payload.getDurationInMinutes());

        if (guideline.getDurationInMinutes() == null) {
            Long defaultSessionDurationTime = 1L;
            guideline.setDurationInMinutes(defaultSessionDurationTime);
        }

        return guidelineRepository.save(guideline);
    }

    public Guideline startSession(Long id) {
        Guideline guideline = findByById(id);

        if (guideline.getFinishedAt() != null) {
            throw new GuidelineSessionAlreadyStartedException();
        }

        guideline.setFinishedAt(LocalDateTime.now().plusMinutes(guideline.getDurationInMinutes()));
        return guidelineRepository.save(guideline);
    }

    public List<Guideline> findAll() {
        return guidelineRepository.findAll();
    }

    public Guideline findByById(Long guidelineId) {
        return guidelineRepository
                .findById(guidelineId)
                .orElseThrow(() -> new GuidelineNotFoundException(guidelineId));
    }
}
