package com.example.gerenciador_sessoes_votacao.v1.constants;

import java.util.stream.Stream;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.VotoInvalidoException;

public enum EnumVotos {
    SIM, NAO;

    public static EnumVotos getInstance(String valorVoto) {
        return Stream.of(values())
                .filter(enumVotos -> enumVotos.name().equalsIgnoreCase(valorVoto))
                .findFirst()
                .orElseThrow(VotoInvalidoException::new);
    }
}
