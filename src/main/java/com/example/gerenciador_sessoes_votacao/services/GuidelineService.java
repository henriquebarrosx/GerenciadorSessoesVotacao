package com.example.gerenciador_sessoes_votacao.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gerenciador_sessoes_votacao.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.repositories.GuidelineRepository;

@Service
@RequiredArgsConstructor
public class GuidelineService {
    private final GuidelineRepository guidelineRepository;

    public List<Guideline> fetchGuidelines() {
        return guidelineRepository.findAll();
    }
}
