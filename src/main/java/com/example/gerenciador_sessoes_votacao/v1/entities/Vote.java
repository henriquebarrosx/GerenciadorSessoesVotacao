package com.example.gerenciador_sessoes_votacao.v1.entities;

import com.example.gerenciador_sessoes_votacao.v1.constants.VotesEnum;
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
    private VotesEnum value;

    @ManyToOne
    @JoinColumn(name = "guideline_id", nullable = false, referencedColumnName = "id")
    private Guideline guideline;

    @Column(nullable = false)
    private String associateCpf;

    public Vote(VotesEnum value, Guideline guideline, String associateCpf) {
        this.value = value;
        this.guideline = guideline;
        this.associateCpf = associateCpf;
    }
}
