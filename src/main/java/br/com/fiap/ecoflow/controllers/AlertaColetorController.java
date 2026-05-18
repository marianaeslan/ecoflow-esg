package br.com.fiap.ecoflow.controllers;

import br.com.fiap.ecoflow.dtos.AlertaColetorResponseDTO;
import br.com.fiap.ecoflow.services.AlertaColetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaColetorController {

    @Autowired
    private AlertaColetorService alertaColetorService;

    @GetMapping
    public ResponseEntity<List<AlertaColetorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(alertaColetorService.listarTodos());
    }

    @GetMapping("/nao-lidos")
    public ResponseEntity<List<AlertaColetorResponseDTO>> listarNaoLidos() {
        return ResponseEntity.ok(alertaColetorService.listarNaoLidos());
    }

    @PatchMapping("/{id}/lido")
    public ResponseEntity<AlertaColetorResponseDTO> marcarComoLido(@PathVariable Long id) {
        return ResponseEntity.ok(alertaColetorService.marcarComoLido(id));
    }
}
