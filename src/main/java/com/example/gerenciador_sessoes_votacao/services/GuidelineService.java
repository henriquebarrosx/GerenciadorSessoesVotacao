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

    public Guideline createGuideline(Guideline guideline) {
        return guidelineRepository.save(guideline);
    }

    public List<Guideline> getGuidelines() {
        return guidelineRepository.findAll();
    }

    public Guideline getGuidelineById(Long guidelineId) {
        return guidelineRepository
                .findById(guidelineId)
                .orElseThrow(() -> new GuidelineNotFoundException(guidelineId));
    }
}
