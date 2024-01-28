package org.gogame.server.controllers;

import lombok.RequiredArgsConstructor;
import org.gogame.server.domain.entities.UserInviteStatus;
import org.gogame.server.domain.entities.dto.*;
import org.gogame.server.service.AuthenticationService;
import org.gogame.server.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;

    @PostMapping("/invite/send")
    public ResponseEntity<UserInviteDto> sendGameInvite (
            @RequestBody UserInviteDto request
    ) {
        System.out.println("dupa");
        UserInviteDto response;
        try {
            response = service.sendGameInvite(request);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/invite/accept")
    public ResponseEntity<UserInviteDto> acceptGameInvite (
            @RequestBody UserInviteDto request
    ) {
        UserInviteDto response;
        try {
            response = service.acceptGameInvite(request);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/invite/reject")
    public ResponseEntity<UserInviteDto> rejectGameInvite (
            @RequestBody UserInviteDto request
    ) {
        UserInviteDto response;
        try {
            response = service.rejectGameInvite(request);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/invite/fetch")
    public ResponseEntity<UserInviteDto> fetchGameInvite (
            @RequestBody UserInviteDto request
    ) {
        UserInviteDto response;
        try {
            response = service.fetchGameInvite(request);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
