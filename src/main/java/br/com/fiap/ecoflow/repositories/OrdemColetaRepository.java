package br.com.fiap.ecoflow.repositories;

import br.com.fiap.ecoflow.models.OrdemColeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdemColetaRepository extends JpaRepository<OrdemColeta, Long> {
    List<OrdemColeta> findByColetorId(Long idColetor);
}

