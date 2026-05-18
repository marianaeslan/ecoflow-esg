package br.com.fiap.ecoflow.repositories;

import br.com.fiap.ecoflow.models.RegistroVolume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroVolumeRepository extends JpaRepository<RegistroVolume, Long> {
    List<RegistroVolume> findByColetorId(Long idColetor);
}

