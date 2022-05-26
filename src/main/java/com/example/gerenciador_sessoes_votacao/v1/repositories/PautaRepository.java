package com.example.gerenciador_sessoes_votacao.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {}
