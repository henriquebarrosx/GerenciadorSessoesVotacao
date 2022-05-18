package com.example.gerenciador_sessoes_votacao.v1.entities;

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

    @Column
    private Long durationInMinutes;

    @Column
    private LocalDateTime finishedAt;

    public Guideline(String title, Long durationInMinutes) {
        this.title = title;
        this.durationInMinutes = durationInMinutes;
    }
}
