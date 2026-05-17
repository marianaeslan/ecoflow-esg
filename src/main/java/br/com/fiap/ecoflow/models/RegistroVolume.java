package br.com.fiap.ecoflow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "REGISTRO_VOLUME")
public class RegistroVolume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGISTRO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_COLETOR", nullable = false)
    private Coletor coletor;

    @Column(name = "VOLUME_LT", precision = 10, scale = 2, nullable = false)
    private BigDecimal volumeLt;

    @Column(name = "PERCENTUAL_USO", precision = 5, scale = 2)
    private BigDecimal percentualUso;

    @Column(name = "ORIGEM_LEITURA", length = 20, nullable = false)
    private String origemLeitura;

    @Column(name = "DT_LEITURA", nullable = false, updatable = false)
    private LocalDate dataLeitura;

    public RegistroVolume() {}

    @PrePersist
    public void prePersist() {
        this.dataLeitura = LocalDate.now();
    }
}

