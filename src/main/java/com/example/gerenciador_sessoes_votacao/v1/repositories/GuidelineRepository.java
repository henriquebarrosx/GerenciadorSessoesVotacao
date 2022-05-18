package com.example.gerenciador_sessoes_votacao.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;

public interface GuidelineRepository extends JpaRepository<Guideline, Long> {}
