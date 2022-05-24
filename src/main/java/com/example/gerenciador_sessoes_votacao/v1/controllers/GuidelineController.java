package com.example.gerenciador_sessoes_votacao.v1.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;
import com.example.gerenciador_sessoes_votacao.v1.services.VoteService;
import com.example.gerenciador_sessoes_votacao.v1.services.GuidelineService;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.GuidelineNewVoteRequest;
import com.example.gerenciador_sessoes_votacao.v1.controllers.dto.GuidelineTotalVotesResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guidelines")
public class GuidelineController {
    private final GuidelineService guidelineService;
    private final VoteService voteService;

    @GetMapping()
    public List<Guideline> getGuidelines() {
        return guidelineService.findAllGuidelines();
    }

    @PostMapping("/new")
    public void createGuideline(@RequestBody Guideline guideline) {
        guidelineService.createNewGuideline(guideline);
    }

    @GetMapping("/{id}")
    public Guideline fetchGuidelineById(@PathVariable(value = "id") Long guidelineId) {
        return guidelineService.findGuideline(guidelineId);
    }

    @GetMapping("/{id}/start")
    public void startGuidelineSession(@PathVariable(value = "id") Long guidelineId) {
        guidelineService.startNewSession(guidelineId);
    }

    @GetMapping("/{id}/votes")
    public GuidelineTotalVotesResponse countTotalVotes(@PathVariable(value = "id") Long guidelineId) {
        Long totalVotes = voteService.getGuidelineVotes(guidelineId);
        return new GuidelineTotalVotesResponse(totalVotes);
    }

    @PostMapping("/{id}/votes/new")
    public void createNewVote(@PathVariable(value = "id") Long guidelineId, @RequestBody GuidelineNewVoteRequest guidelineNewVote) {
        voteService.createNewVote(guidelineId, guidelineNewVote);
    }
}