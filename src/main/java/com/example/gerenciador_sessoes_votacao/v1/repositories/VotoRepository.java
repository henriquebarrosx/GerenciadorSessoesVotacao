package com.example.gerenciador_sessoes_votacao.v1.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gerenciador_sessoes_votacao.v1.entities.Voto;
import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> buscarVotosPorPauta(Pauta pauta);
    Optional<Voto> buscarVotosPorAssociadoCpfEPauta(String associateCpf, Pauta pauta);

}
