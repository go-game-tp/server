package org.gogame.server.service;

import lombok.RequiredArgsConstructor;
import org.gogame.server.domain.entities.dto.AuthResponseDto;
import org.gogame.server.domain.entities.UserEntity;
import org.gogame.server.domain.entities.dto.UserLoginDto;
import org.gogame.server.domain.entities.dto.UserRegisterDto;
import org.gogame.server.mappers.Mapper;
import org.gogame.server.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final Mapper<UserRegisterDto, UserEntity> userRegisterMapper;

    public AuthResponseDto register(UserRegisterDto dto) {
        UserEntity registeredUser = userRegisterMapper.mapTo(dto);
        userRepo.save(registeredUser);
        var jwtToken = jwtService.generateToken(registeredUser);
        return AuthResponseDto.builder()
                .userId(registeredUser.getUserId())
                .token(jwtToken)
                .build();
    }

    public AuthResponseDto authenticate(UserLoginDto dto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getNickname(),
                        dto.getPassword()
                )
        );

        var loggedUser = userRepo.findByNickname(dto.getNickname())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(loggedUser);
        return AuthResponseDto.builder()
                .userId(loggedUser.getUserId())
                .token(jwtToken)
                .build();
    }
}
