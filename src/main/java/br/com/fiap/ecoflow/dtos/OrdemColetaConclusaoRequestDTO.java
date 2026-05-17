package br.com.fiap.ecoflow.dtos;

import jakarta.validation.constraints.Size;

public record OrdemColetaConclusaoRequestDTO(
        @Size(max = 300, message = "A observação deve ter no máximo 300 caracteres")
        String observacao
) {}

