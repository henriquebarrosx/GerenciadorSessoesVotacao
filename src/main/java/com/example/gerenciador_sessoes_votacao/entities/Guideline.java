package com.example.gerenciador_sessoes_votacao.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Guideline {

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

    public Guideline(String title, LocalDateTime finishedAt) {
        this.title = title;
        this.finishedAt = finishedAt;
    }
}
