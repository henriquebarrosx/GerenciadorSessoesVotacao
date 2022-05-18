package com.example.gerenciador_sessoes_votacao.controllers;

import java.util.List;
import com.example.gerenciador_sessoes_votacao.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.services.GuidelineService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GuidelineController {
    private final GuidelineService guidelineService;

    @GetMapping("/guidelines/{id}")
    public Guideline fetchGuidelineById(@PathVariable(value = "id") Long guidelineId) {
        return guidelineService.getGuidelineById(guidelineId);
    }

    @GetMapping("/guidelines")
    public List<Guideline> getGuidelines() {
        return guidelineService.getGuidelines();
    }
}
