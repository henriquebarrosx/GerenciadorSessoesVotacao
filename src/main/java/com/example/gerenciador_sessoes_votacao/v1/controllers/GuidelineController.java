package com.example.gerenciador_sessoes_votacao.v1.controllers;

import java.util.List;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.services.GuidelineService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GuidelineController {
    private final GuidelineService guidelineService;

    @PostMapping("/guidelines/new")
    public Guideline createGuideline(@RequestBody Guideline guideline) {
        return guidelineService.create(guideline);
    }

    @PutMapping("/guidelines/{id}/edit")
    public Guideline startGuidelineSession(@PathVariable(value = "id") Long guidelineId) {
        return guidelineService.startSession(guidelineId);
    }

    @GetMapping("/guidelines/{id}")
    public Guideline fetchGuidelineById(@PathVariable(value = "id") Long guidelineId) {
        return guidelineService.findByById(guidelineId);
    }

    @GetMapping("/guidelines")
    public List<Guideline> getGuidelines() {
        return guidelineService.findAll();
    }
}
