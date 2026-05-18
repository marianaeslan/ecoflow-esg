package br.com.fiap.ecoflow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "COLETOR")
public class Coletor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coletor")
    @SequenceGenerator(name = "seq_coletor", sequenceName = "SEQ_COLETOR", allocationSize = 1)
    @Column(name = "ID_COLETOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_SETOR", nullable = false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO", nullable = false)
    private TipoResiduo tipoResiduo;

    @Column(name = "CODIGO", length = 20, nullable = false, unique = true)
    private String codigo;

    @Column(name = "LOCALIZACAO", length = 150, nullable = false)
    private String localizacao;

    @Column(name = "CAPACIDADE_LT", precision = 10, scale = 2, nullable = false)
    private BigDecimal capacidadeLt;

    @Column(name = "STATUS", length = 20, nullable = false)
    private String status;

    @Column(name = "DT_INSTALACAO", nullable = false, updatable = false)
    private LocalDate dataInstalacao;

    public Coletor() {}

    @PrePersist
    public void prePersist() {
        this.dataInstalacao = LocalDate.now();
    }
}

