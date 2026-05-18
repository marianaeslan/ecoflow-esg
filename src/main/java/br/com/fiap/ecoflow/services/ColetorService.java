package br.com.fiap.ecoflow.services;

import br.com.fiap.ecoflow.dtos.ColetorRequestDTO;
import br.com.fiap.ecoflow.dtos.ColetorResponseDTO;
import br.com.fiap.ecoflow.models.Coletor;
import br.com.fiap.ecoflow.models.Setor;
import br.com.fiap.ecoflow.models.TipoResiduo;
import br.com.fiap.ecoflow.repositories.ColetorRepository;
import br.com.fiap.ecoflow.repositories.SetorRepository;
import br.com.fiap.ecoflow.repositories.TipoResiduoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColetorService {

    private final ColetorRepository coletorRepository;
    private final SetorRepository setorRepository;
    private final TipoResiduoRepository tipoResiduoRepository;

    public ColetorService(ColetorRepository coletorRepository,
                          SetorRepository setorRepository,
                          TipoResiduoRepository tipoResiduoRepository) {
        this.coletorRepository = coletorRepository;
        this.setorRepository = setorRepository;
        this.tipoResiduoRepository = tipoResiduoRepository;
    }

    public List<ColetorResponseDTO> listarTodos() {
        return coletorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ColetorResponseDTO buscarPorId(Long id) {
        Coletor coletor = findOrThrow(id);
        return toDTO(coletor);
    }

    public ColetorResponseDTO criar(ColetorRequestDTO dto) {
        Setor setor = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com id: " + dto.idSetor()));

        TipoResiduo tipo = tipoResiduoRepository.findById(dto.idTipo())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de resíduo não encontrado com id: " + dto.idTipo()));

        Coletor coletor = new Coletor();
        coletor.setCodigo(dto.codigo());
        coletor.setLocalizacao(dto.localizacao());
        coletor.setCapacidadeLt(dto.capacidadeLt());
        coletor.setSetor(setor);
        coletor.setTipoResiduo(tipo);
        coletor.setStatus("ATIVO");

        return toDTO(coletorRepository.save(coletor));
    }

    public ColetorResponseDTO atualizar(Long id, ColetorRequestDTO dto) {
        Coletor coletor = findOrThrow(id);

        Setor setor = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com id: " + dto.idSetor()));

        TipoResiduo tipo = tipoResiduoRepository.findById(dto.idTipo())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de resíduo não encontrado com id: " + dto.idTipo()));

        coletor.setCodigo(dto.codigo());
        coletor.setLocalizacao(dto.localizacao());
        coletor.setCapacidadeLt(dto.capacidadeLt());
        coletor.setSetor(setor);
        coletor.setTipoResiduo(tipo);

        return toDTO(coletorRepository.save(coletor));
    }

    public void deletar(Long id) {
        findOrThrow(id);
        coletorRepository.deleteById(id);
    }

    private Coletor findOrThrow(Long id) {
        return coletorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coletor não encontrado com id: " + id));
    }

    private ColetorResponseDTO toDTO(Coletor coletor) {
        return new ColetorResponseDTO(
                coletor.getId(),
                coletor.getCodigo(),
                coletor.getLocalizacao(),
                coletor.getCapacidadeLt(),
                coletor.getStatus(),
                coletor.getSetor().getNome(),
                coletor.getTipoResiduo().getNome()
        );
    }
}
