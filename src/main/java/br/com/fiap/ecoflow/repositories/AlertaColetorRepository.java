package br.com.fiap.ecoflow.repositories;

import br.com.fiap.ecoflow.models.AlertaColetor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertaColetorRepository extends JpaRepository<AlertaColetor, Long> {

    List<AlertaColetor> findByLido(Character lido);
}

