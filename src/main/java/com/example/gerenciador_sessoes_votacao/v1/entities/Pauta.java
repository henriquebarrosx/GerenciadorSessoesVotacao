package com.example.gerenciador_sessoes_votacao.v1.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private Long duracaoEmMinutos;

    @Column
    private LocalDateTime horarioEncerramento = null;

    public Pauta(String titulo, Long duracaoEmMinutos) {
        this.titulo = titulo;
        this.duracaoEmMinutos = duracaoEmMinutos;
    }
}
