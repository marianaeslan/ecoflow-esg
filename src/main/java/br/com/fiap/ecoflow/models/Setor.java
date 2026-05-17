package br.com.fiap.ecoflow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "SETOR")
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SETOR")
    private Long id;

    @Column(name = "NOME_SETOR", length = 100, nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", length = 200)
    private String descricao;

    @Column(name = "RESPONSAVEL", length = 100, nullable = false)
    private String responsavel;

    @Column(name = "DT_CADASTRO", nullable = false, updatable = false)
    private LocalDate dataCadastro;

    public Setor() {}

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }
}
