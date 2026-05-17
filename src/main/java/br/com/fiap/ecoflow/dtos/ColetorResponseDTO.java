package br.com.fiap.ecoflow.dtos;

import java.math.BigDecimal;

public record ColetorResponseDTO(
        Long id,
        String codigo,
        String localizacao,
        BigDecimal capacidadeLt,
        String status,
        String nomeSetor,
        String nomeTipoResiduo
) {}

