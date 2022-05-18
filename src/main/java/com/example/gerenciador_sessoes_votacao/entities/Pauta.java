package com.example.gerenciador_sessoes_votacao.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity
@NoArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime finishedAt;

    @Column(nullable = false)
    private Boolean inProgress = false;

    public Pauta(String title, LocalDateTime finishedAt) {
        this.title = title;
        this.finishedAt = finishedAt;
    }
}
