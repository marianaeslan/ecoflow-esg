package br.com.fiap.ecoflow.services;

import br.com.fiap.ecoflow.dtos.TipoResiduoResponseDTO;
import br.com.fiap.ecoflow.models.TipoResiduo;
import br.com.fiap.ecoflow.repositories.TipoResiduoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoResiduoService {

    private final TipoResiduoRepository tipoResiduoRepository;

    public TipoResiduoService(TipoResiduoRepository tipoResiduoRepository) {
        this.tipoResiduoRepository = tipoResiduoRepository;
    }

    public List<TipoResiduoResponseDTO> listarTodos() {
        return tipoResiduoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public TipoResiduoResponseDTO buscarPorId(Long id) {
        TipoResiduo tipo = tipoResiduoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de resíduo não encontrado com id: " + id));
        return toDTO(tipo);
    }

    private TipoResiduoResponseDTO toDTO(TipoResiduo tipo) {
        return new TipoResiduoResponseDTO(
                tipo.getId(),
                tipo.getNome(),
                tipo.getClassificacao()
        );
    }
}
