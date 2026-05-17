package br.com.fiap.ecoflow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ColetorRequestDTO(
        @NotBlank(message = "O código do coletor é obrigatório")
        String codigo,

        @NotBlank(message = "A localização do coletor é obrigatória")
        String localizacao,

        @NotNull(message = "A capacidade em litros é obrigatória")
        @Positive(message = "A capacidade em litros deve ser maior que zero")
        BigDecimal capacidadeLt,

        @NotNull(message = "O setor é obrigatório")
        Long idSetor,

        @NotNull(message = "O tipo de resíduo é obrigatório")
        Long idTipo
) {}

