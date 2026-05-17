package br.com.fiap.ecoflow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "ORDEM_COLETA")
public class OrdemColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEM")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_COLETOR", nullable = false)
    private Coletor coletor;

    @Column(name = "DESTINACAO", length = 60, nullable = false)
    private String destinacao;

    @Column(name = "ORIGEM_ORDEM", length = 20, nullable = false)
    private String origemOrdem;

    @Column(name = "STATUS", length = 20, nullable = false)
    private String status;

    @Column(name = "DT_ABERTURA", nullable = false, updatable = false)
    private LocalDate dataAbertura;

    @Column(name = "DT_CONCLUSAO")
    private LocalDate dataConclusao;

    @Column(name = "OBSERVACAO", length = 300)
    private String observacao;

    public OrdemColeta() {}

    @PrePersist
    public void prePersist() {
        this.dataAbertura = LocalDate.now();
    }
}

