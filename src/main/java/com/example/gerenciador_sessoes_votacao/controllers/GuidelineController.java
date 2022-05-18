package com.example.gerenciador_sessoes_votacao.controllers;

import java.util.List;
import com.example.gerenciador_sessoes_votacao.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.services.GuidelineService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GuidelineController {
    private final GuidelineService guidelineService;

    @GetMapping("/guidelines")
    public List<Guideline> getGuidelines() {
        return guidelineService.fetchGuidelines();
    }
}
