package com.example.gerenciador_sessoes_votacao.v1.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
    private LocalDateTime finishedAt = null;

    public Guideline(String title, Long durationInMinutes) {
        this.title = title;
        this.durationInMinutes = durationInMinutes;
    }
}
