package br.com.fiap.ecoflow.controllers;

import br.com.fiap.ecoflow.dtos.RegistroVolumeRequestDTO;
import br.com.fiap.ecoflow.dtos.RegistroVolumeResponseDTO;
import br.com.fiap.ecoflow.services.RegistroVolumeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coletores/{idColetor}/registros")
public class RegistroVolumeController {

    @Autowired
    private RegistroVolumeService registroVolumeService;

    @GetMapping
    public ResponseEntity<List<RegistroVolumeResponseDTO>> listarPorColetor(@PathVariable Long idColetor) {
        return ResponseEntity.ok(registroVolumeService.listarPorColetor(idColetor));
    }

    @PostMapping
    public ResponseEntity<RegistroVolumeResponseDTO> registrar(@PathVariable Long idColetor,
                                                                @RequestBody @Valid RegistroVolumeRequestDTO dto) {
        return ResponseEntity.status(201).body(registroVolumeService.registrar(idColetor, dto));
    }
}
