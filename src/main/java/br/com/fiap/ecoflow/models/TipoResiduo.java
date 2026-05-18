package br.com.fiap.ecoflow.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_RESIDUO")
public class TipoResiduo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_residuo")
    @SequenceGenerator(name = "seq_tipo_residuo", sequenceName = "SEQ_TIPO_RESIDUO", allocationSize = 1)
    @Column(name = "ID_TIPO")
    private Long id;

    @Column(name = "NOME_RESIDUO", length = 100, nullable = false)
    private String nome;

    @Column(name = "CLASSIFICACAO", length = 20, nullable = false)
    private String classificacao;

    @Column(name = "DESTINACAO_PAD", length = 60, nullable = false)
    private String destinacaoPadrao;

    @Column(name = "PERICULOSIDADE", length = 20, nullable = false)
    private String periculosidade;

    public TipoResiduo() {}

}
