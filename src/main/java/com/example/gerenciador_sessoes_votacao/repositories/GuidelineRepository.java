package com.example.gerenciador_sessoes_votacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gerenciador_sessoes_votacao.entities.Guideline;

public interface GuidelineRepository extends JpaRepository<Guideline, Long> {}
