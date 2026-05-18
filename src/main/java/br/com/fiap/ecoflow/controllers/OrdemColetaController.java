package br.com.fiap.ecoflow.controllers;

import br.com.fiap.ecoflow.dtos.OrdemColetaConclusaoRequestDTO;
import br.com.fiap.ecoflow.dtos.OrdemColetaResponseDTO;
import br.com.fiap.ecoflow.services.OrdemColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ordens-coleta")
public class OrdemColetaController {

    @Autowired
    private OrdemColetaService ordemColetaService;

    @GetMapping
    public ResponseEntity<List<OrdemColetaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(ordemColetaService.listarTodas());
    }

    @GetMapping("/coletor/{idColetor}")
    public ResponseEntity<List<OrdemColetaResponseDTO>> listarPorColetor(@PathVariable Long idColetor) {
        return ResponseEntity.ok(ordemColetaService.listarPorColetor(idColetor));
    }

    @PostMapping("/coletor/{idColetor}")
    public ResponseEntity<OrdemColetaResponseDTO> criarManual(@PathVariable Long idColetor) {
        return ResponseEntity.status(201).body(ordemColetaService.criarManual(idColetor));
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<OrdemColetaResponseDTO> concluir(@PathVariable Long id,
                                                            @RequestBody @Valid OrdemColetaConclusaoRequestDTO dto) {
        return ResponseEntity.ok(ordemColetaService.concluir(id, dto));
    }
}
