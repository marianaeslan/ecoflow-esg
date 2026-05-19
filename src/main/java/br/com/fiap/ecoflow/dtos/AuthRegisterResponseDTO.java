package br.com.fiap.ecoflow.dtos;

public record AuthRegisterResponseDTO(
        Long id,
        String username,
        String role
) {
}

