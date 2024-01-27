package org.gogame.server.controllers;

import lombok.RequiredArgsConstructor;
import org.gogame.server.domain.entities.dto.AuthResponseDto;
import org.gogame.server.domain.entities.dto.UserLoginDto;
import org.gogame.server.domain.entities.dto.UserRegisterDto;
import org.gogame.server.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register (
            @RequestBody UserRegisterDto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate (
            @RequestBody UserLoginDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
