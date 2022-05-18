package com.example.gerenciador_sessoes_votacao.v1.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.repositories.GuidelineRepository;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.GuidelineNotFoundException;

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
