package org.gogame.server.controller;

import org.gogame.server.mappers.Mapper;
import org.gogame.server.domain.entities.UserBioEntity;
import org.gogame.server.domain.entities.UserEntity;
import org.gogame.server.domain.entities.dto.UserProfileDto;
import org.gogame.server.repositories.UserBioRepository;
import org.gogame.server.repositories.UserRepository;
import org.gogame.server.services.UserProfileService;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;


@RestController
public class UserProfileController {

    private final UserRepository userRepo;
    private final UserBioRepository userBioRepo;
    private final UserProfileService userProfileService;

    private final Mapper<Pair<UserEntity, UserBioEntity>, UserProfileDto> userProfileMapper;

    public UserProfileController(UserProfileService userProfileService,
                                 Mapper<Pair<UserEntity, UserBioEntity>, UserProfileDto> userProfileMapper,
                                 UserRepository userRepo,
                                 UserBioRepository userBioRepo) {

        this.userProfileService = userProfileService;
        this.userProfileMapper = userProfileMapper;
        this.userRepo = userRepo;
        this.userBioRepo = userBioRepo;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileDto> getUserInfo(@PathVariable Long id) {

        userRepo.save(UserEntity.builder()
                .userId(1L)
                .nickname("Grzegorz")
                .passwordHash("dsfbszvbsdiovwaklc")
                .joinDate(Timestamp.valueOf("2021-01-01 00:00:00"))
                .email("grzegorz.brzenczyszczykiewicz@spring.java")
                .build()
        );

        userBioRepo.save(UserBioEntity.builder()
                .userId(1L)
                .bio("pożyteczny boilerplate")
                .build()
        );

        var userInfoOptional = userProfileService.getUserInfo(id);
        if (userInfoOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var userProfile = Pair.of(
                userInfoOptional.get(),
                userProfileService.getUserBio(id).orElse(new UserBioEntity())
        );

        return new ResponseEntity<>(userProfileMapper.mapTo(userProfile), HttpStatus.OK);
    }
}
