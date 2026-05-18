package br.com.fiap.ecoflow.services;

import br.com.fiap.ecoflow.dtos.AlertaColetorResponseDTO;
import br.com.fiap.ecoflow.models.AlertaColetor;
import br.com.fiap.ecoflow.repositories.AlertaColetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaColetorService {

    private final AlertaColetorRepository alertaColetorRepository;

    public AlertaColetorService(AlertaColetorRepository alertaColetorRepository) {
        this.alertaColetorRepository = alertaColetorRepository;
    }

    public List<AlertaColetorResponseDTO> listarTodos() {
        return alertaColetorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<AlertaColetorResponseDTO> listarNaoLidos() {
        return alertaColetorRepository.findByLido('N')
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public AlertaColetorResponseDTO marcarComoLido(Long id) {
        AlertaColetor alerta = alertaColetorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado com id: " + id));

        alerta.setLido('S');
        return toDTO(alertaColetorRepository.save(alerta));
    }

    private AlertaColetorResponseDTO toDTO(AlertaColetor a) {
        return new AlertaColetorResponseDTO(
                a.getId(),
                a.getMensagem(),
                a.getTipoAlerta(),
                a.getPercentualUso(),
                a.getDataAlerta()
        );
    }
}
