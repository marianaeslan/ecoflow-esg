package br.com.fiap.ecoflow.services;

import br.com.fiap.ecoflow.dtos.RegistroVolumeRequestDTO;
import br.com.fiap.ecoflow.dtos.RegistroVolumeResponseDTO;
import br.com.fiap.ecoflow.models.Coletor;
import br.com.fiap.ecoflow.models.RegistroVolume;
import br.com.fiap.ecoflow.repositories.ColetorRepository;
import br.com.fiap.ecoflow.repositories.RegistroVolumeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RegistroVolumeService {

    private final RegistroVolumeRepository registroVolumeRepository;
    private final ColetorRepository coletorRepository;

    public RegistroVolumeService(RegistroVolumeRepository registroVolumeRepository,
                                  ColetorRepository coletorRepository) {
        this.registroVolumeRepository = registroVolumeRepository;
        this.coletorRepository = coletorRepository;
    }

    public List<RegistroVolumeResponseDTO> listarPorColetor(Long idColetor) {
        return registroVolumeRepository.findByColetorId(idColetor)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public RegistroVolumeResponseDTO registrar(Long idColetor, RegistroVolumeRequestDTO dto) {
        Coletor coletor = coletorRepository.findById(idColetor)
                .orElseThrow(() -> new EntityNotFoundException("Coletor não encontrado com id: " + idColetor));

        if (!coletor.getStatus().equals("ATIVO")) {
            throw new IllegalStateException("Não é possível registrar volume para um coletor com status: " + coletor.getStatus());
        }

        BigDecimal volume = BigDecimal.valueOf(dto.volumeLt());
        BigDecimal percentual = volume
                .divide(coletor.getCapacidadeLt(), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);

        RegistroVolume registro = new RegistroVolume();
        registro.setColetor(coletor);
        registro.setVolumeLt(volume);
        registro.setPercentualUso(percentual);
        registro.setOrigemLeitura(dto.origemLeitura());

        return toDTO(registroVolumeRepository.save(registro));
    }

    private RegistroVolumeResponseDTO toDTO(RegistroVolume r) {
        return new RegistroVolumeResponseDTO(
                r.getId(),
                r.getColetor().getId(),
                r.getVolumeLt().doubleValue(),
                r.getPercentualUso().doubleValue(),
                r.getOrigemLeitura(),
                r.getDataLeitura().atStartOfDay()
        );
    }
}
