package br.com.fiap.ecoflow.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AlertaColetorResponseDTO(
        Long id,
        String mensagem,
        String tipoAlerta,
        BigDecimal percentualUso,
        LocalDate dataAlerta
) {}

