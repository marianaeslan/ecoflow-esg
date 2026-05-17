package br.com.fiap.ecoflow.dtos;

import java.time.LocalDate;

public record OrdemColetaResponseDTO(
        Long id,
        Long idColetor,
        String codigoColetor,
        String destinacao,
        String origemOrdem,
        String status,
        LocalDate dataAbertura,
        LocalDate dataConclusao,
        String observacao
) {}

