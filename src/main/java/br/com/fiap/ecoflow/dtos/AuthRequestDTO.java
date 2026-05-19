package br.com.fiap.ecoflow.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "Username é obrigatório")
        String username,
        @NotBlank(message = "Password é obrigatório")
        String password
) {
}

