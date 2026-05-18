package br.com.fiap.ecoflow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ALERTA_COLETOR")
public class AlertaColetor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alerta_coletor")
    @SequenceGenerator(name = "seq_alerta_coletor", sequenceName = "SEQ_ALERTA_COLETOR", allocationSize = 1)
    @Column(name = "ID_ALERTA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_COLETOR", nullable = false)
    private Coletor coletor;

    @Column(name = "TIPO_ALERTA", length = 30, nullable = false)
    private String tipoAlerta;

    @Column(name = "MENSAGEM", length = 500, nullable = false)
    private String mensagem;

    @Column(name = "PERCENTUAL_USO", precision = 5, scale = 2)
    private BigDecimal percentualUso;

    @Column(name = "DT_ALERTA", nullable = false, updatable = false)
    private LocalDate dataAlerta;

    @Column(name = "LIDO", length = 1, nullable = false)
    private Character lido;

    public AlertaColetor() {}

    @PrePersist
    public void prePersist() {
        this.dataAlerta = LocalDate.now();
        if (this.lido == null) {
            this.lido = 'N';
        }
    }
}

