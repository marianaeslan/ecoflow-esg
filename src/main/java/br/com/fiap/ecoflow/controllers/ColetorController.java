package br.com.fiap.ecoflow.controllers;

import br.com.fiap.ecoflow.dtos.ColetorRequestDTO;
import br.com.fiap.ecoflow.dtos.ColetorResponseDTO;
import br.com.fiap.ecoflow.services.ColetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coletores")
public class ColetorController {

    @Autowired
    private ColetorService coletorService;

    @GetMapping
    public ResponseEntity<List<ColetorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(coletorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColetorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(coletorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ColetorResponseDTO> criar(@RequestBody @Valid ColetorRequestDTO dto) {
        return ResponseEntity.status(201).body(coletorService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColetorResponseDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid ColetorRequestDTO dto) {
        return ResponseEntity.ok(coletorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        coletorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
