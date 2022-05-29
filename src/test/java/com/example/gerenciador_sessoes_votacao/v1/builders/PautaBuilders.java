package com.example.gerenciador_sessoes_votacao.v1.builders;

import java.util.List;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import com.example.gerenciador_sessoes_votacao.v1.entities.Pauta;

public class PautaBuilders {

    public static List<Pauta> obterListaDePautas() {
        return List.of(
                obterUmaPauta(),
                obterUmaPauta(),
                obterUmaPauta(),
                obterUmaPauta()
        );
    }

    public static Pauta obterUmaPauta() {
        return Pauta.builder()
                .id(Mockito.any(Long.class))
                .titulo("BK > MC")
                .duracaoEmMinutos(10L)
                .horarioEncerramento(LocalDateTime.now().plusMinutes(10))
                .build();
    }

}
