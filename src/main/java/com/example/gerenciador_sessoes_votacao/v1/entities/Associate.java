package com.example.gerenciador_sessoes_votacao.v1.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Table
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Associate {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String cpf;

    @Column(nullable = false)
    private String fullName;
}
