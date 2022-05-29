package com.example.gerenciador_sessoes_votacao.v1.entities;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.example.gerenciador_sessoes_votacao.v1.constants.EnumVotos;

@Table
@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private EnumVotos valor;

    @ManyToOne
    @JoinColumn
    private Pauta pauta;

    @Column(nullable = false)
    private String associadoCpf;

    public Voto(EnumVotos valor, Pauta pauta, String associadoCpf) {
        this.valor = valor;
        this.pauta = pauta;
        this.associadoCpf = associadoCpf;
    }
}
