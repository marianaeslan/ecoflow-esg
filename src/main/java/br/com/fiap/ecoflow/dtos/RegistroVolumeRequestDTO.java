package br.com.fiap.ecoflow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistroVolumeRequestDTO(
        @NotNull(message = "O volume lido é obrigatório")
        @Positive(message = "O volume em litros deve ser maior que zero")
        Double volumeLt,

        @NotBlank(message = "A origem da leitura não pode estar em branco")
        String origemLeitura
) {}
