package com.example.gerenciador_sessoes_votacao.services;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.repositories.GuidelineRepository;
import com.example.gerenciador_sessoes_votacao.exceptions.GuidelineNotFoundException;

@Service
@RequiredArgsConstructor
public class GuidelineService {
    private final GuidelineRepository guidelineRepository;

    public List<Guideline> getGuidelines() {
        return guidelineRepository.findAll();
    }

    public Guideline getGuidelineById(Long guidelineId) {
        Optional<Guideline> guideline = guidelineRepository.findById(guidelineId);

        if (guideline.isEmpty()) {
            throw new GuidelineNotFoundException(guidelineId);
        }

        return guideline.get();
    }
}
