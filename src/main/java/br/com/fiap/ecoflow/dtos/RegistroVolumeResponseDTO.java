package br.com.fiap.ecoflow.dtos;

import java.time.LocalDateTime;

public record RegistroVolumeResponseDTO(
        Long idRegistro,
        Long idColetor,
        Double volumeLt,
        Double percentualUso,
        String origemLeitura,
        LocalDateTime dataLeitura
) {}
