package org.gogame.server.services.impl;

import org.gogame.server.domain.entities.UserBioEntity;
import org.gogame.server.domain.entities.UserEntity;
import org.gogame.server.repositories.UserBioRepository;
import org.gogame.server.repositories.UserRepository;
import org.gogame.server.services.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepo;
    private final UserBioRepository userBioRepo;

    public UserProfileServiceImpl(UserRepository userRepo, UserBioRepository userBioRepo) {
        this.userRepo = userRepo;
        this.userBioRepo = userBioRepo;
    }

    @Override
    public Optional<UserEntity> getUserInfo(Long userId) {

        if (userId != null) {
            return userRepo.findById(userId);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserBioEntity> getUserBio(Long userId) {

        if (userId != null) {
            return userBioRepo.findById(userId);
        } else {
            return Optional.empty();
        }
    }
}
