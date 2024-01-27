package org.gogame.server.service;

import lombok.RequiredArgsConstructor;
import org.gogame.server.domain.entities.dto.AuthResponseDto;
import org.gogame.server.domain.entities.UserEntity;
import org.gogame.server.domain.entities.dto.UserLoginDto;
import org.gogame.server.domain.entities.dto.UserRegisterDto;
import org.gogame.server.mappers.Mapper;
import org.gogame.server.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final Mapper<UserRegisterDto, UserEntity> userRegisterMapper;

    public AuthResponseDto register(UserRegisterDto dto) throws SQLException {
        UserEntity registeredUser = userRegisterMapper.mapTo(dto);
        try {
            userRepo.save(registeredUser);
        } catch (DataIntegrityViolationException ex) {
            throw new SQLException("User with this nickname already exists");
        }
        var jwtToken = jwtService.generateToken(registeredUser);
        return AuthResponseDto.builder()
                .userId(registeredUser.getUserId())
                .token(jwtToken)
                .build();
    }

    public AuthResponseDto authenticate(UserLoginDto dto) throws SQLException {
        UserEntity loggedUser;
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getNickname(),
                            dto.getPassword()
                    )
            );

            loggedUser = userRepo.findByNickname(dto.getNickname()).orElseThrow();
        } catch (AuthenticationException | NullPointerException ex) {
            throw new SQLException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(loggedUser);
        return AuthResponseDto.builder()
                .userId(loggedUser.getUserId())
                .token(jwtToken)
                .build();
    }
}
