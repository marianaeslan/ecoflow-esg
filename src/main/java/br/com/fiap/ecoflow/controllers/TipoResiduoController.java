package br.com.fiap.ecoflow.controllers;

import br.com.fiap.ecoflow.dtos.TipoResiduoResponseDTO;
import br.com.fiap.ecoflow.services.TipoResiduoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-residuo")
public class TipoResiduoController {

    @Autowired
    private TipoResiduoService tipoResiduoService;

    @GetMapping
    public ResponseEntity<List<TipoResiduoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tipoResiduoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoResiduoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoResiduoService.buscarPorId(id));
    }
}
