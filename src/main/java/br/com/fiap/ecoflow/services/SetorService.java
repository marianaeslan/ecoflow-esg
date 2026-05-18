package br.com.fiap.ecoflow.services;

import br.com.fiap.ecoflow.dtos.SetorResponseDTO;
import br.com.fiap.ecoflow.models.Setor;
import br.com.fiap.ecoflow.repositories.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<SetorResponseDTO> listarTodos() {
        return setorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SetorResponseDTO buscarPorId(Long id) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com id: " + id));
        return toDTO(setor);
    }

    private SetorResponseDTO toDTO(Setor setor) {
        return new SetorResponseDTO(
                setor.getId(),
                setor.getNome(),
                setor.getResponsavel()
        );
    }
}
