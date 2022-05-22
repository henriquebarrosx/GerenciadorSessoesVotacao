package com.example.gerenciador_sessoes_votacao.v1.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Table
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "guideline_id", nullable = false)
    private Guideline guideline;

    @OneToOne
    @JoinColumn(name = "associate_id", nullable = false)
    private Associate associate;

    public Vote(String value, Guideline guideline, Associate associate) {
        this.value = value;
        this.guideline = guideline;
        this.associate = associate;
    }
}
