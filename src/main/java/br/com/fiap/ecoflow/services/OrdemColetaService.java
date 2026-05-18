package br.com.fiap.ecoflow.services;

import br.com.fiap.ecoflow.dtos.OrdemColetaConclusaoRequestDTO;
import br.com.fiap.ecoflow.dtos.OrdemColetaResponseDTO;
import br.com.fiap.ecoflow.models.Coletor;
import br.com.fiap.ecoflow.models.OrdemColeta;
import br.com.fiap.ecoflow.repositories.ColetorRepository;
import br.com.fiap.ecoflow.repositories.OrdemColetaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdemColetaService {

    private final OrdemColetaRepository ordemColetaRepository;
    private final ColetorRepository coletorRepository;

    public OrdemColetaService(OrdemColetaRepository ordemColetaRepository,
                               ColetorRepository coletorRepository) {
        this.ordemColetaRepository = ordemColetaRepository;
        this.coletorRepository = coletorRepository;
    }

    public List<OrdemColetaResponseDTO> listarTodas() {
        return ordemColetaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<OrdemColetaResponseDTO> listarPorColetor(Long idColetor) {
        return ordemColetaRepository.findByColetorId(idColetor)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public OrdemColetaResponseDTO criarManual(Long idColetor) {
        Coletor coletor = coletorRepository.findById(idColetor)
                .orElseThrow(() -> new EntityNotFoundException("Coletor não encontrado com id: " + idColetor));

        OrdemColeta ordem = new OrdemColeta();
        ordem.setColetor(coletor);
        ordem.setDestinacao(coletor.getTipoResiduo().getDestinacaoPadrao());
        ordem.setOrigemOrdem("MANUAL");
        ordem.setStatus("ABERTA");

        return toDTO(ordemColetaRepository.save(ordem));
    }

    public OrdemColetaResponseDTO concluir(Long id, OrdemColetaConclusaoRequestDTO dto) {
        OrdemColeta ordem = ordemColetaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de coleta não encontrada com id: " + id));

        if (ordem.getStatus().equals("CONCLUIDA")) {
            throw new IllegalStateException("Ordem já está concluída.");
        }

        ordem.setStatus("CONCLUIDA");
        ordem.setDataConclusao(LocalDate.now());
        ordem.setObservacao(dto.observacao());

        return toDTO(ordemColetaRepository.save(ordem));
    }

    private OrdemColetaResponseDTO toDTO(OrdemColeta o) {
        return new OrdemColetaResponseDTO(
                o.getId(),
                o.getColetor().getId(),
                o.getColetor().getCodigo(),
                o.getDestinacao(),
                o.getOrigemOrdem(),
                o.getStatus(),
                o.getDataAbertura(),
                o.getDataConclusao(),
                o.getObservacao()
        );
    }
}
