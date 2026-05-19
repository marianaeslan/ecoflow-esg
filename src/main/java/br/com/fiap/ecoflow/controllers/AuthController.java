package br.com.fiap.ecoflow.controllers;

import br.com.fiap.ecoflow.config.TokenService;
import br.com.fiap.ecoflow.dtos.AuthRegisterRequestDTO;
import br.com.fiap.ecoflow.dtos.AuthRegisterResponseDTO;
import br.com.fiap.ecoflow.dtos.AuthRequestDTO;
import br.com.fiap.ecoflow.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthRequestDTO authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken(auth.getName());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponseDTO> register(@RequestBody @Valid AuthRegisterRequestDTO request) {
        var usuario = usuarioService.register(request);
        var response = new AuthRegisterResponseDTO(usuario.getId(), usuario.getUsername(), usuario.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
