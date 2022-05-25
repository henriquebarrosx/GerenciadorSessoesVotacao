package com.example.gerenciador_sessoes_votacao.v1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gerenciador_sessoes_votacao.v1.entities.Vote;
import com.example.gerenciador_sessoes_votacao.v1.entities.Guideline;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByGuideline(Guideline guideline);
    Optional<Vote> findByAssociateCpfAndGuideline(String associateCpf, Guideline guideline);

}
