package com.example.gerenciador_sessoes_votacao.v1.constants;

import java.util.stream.Stream;
import com.example.gerenciador_sessoes_votacao.v1.exceptions.InvalidVoteException;

public enum VotesEnum {
    SIM, NAO;

    public static VotesEnum getInstance(String voteValue) {
        return Stream.of(values())
                .filter(votesEnum -> votesEnum.name().equalsIgnoreCase(voteValue))
                .findFirst()
                .orElseThrow(() -> new InvalidVoteException());
    }
}
