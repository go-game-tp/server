package org.gogame.server.services;

import org.gogame.server.domain.entities.UserBioEntity;
import org.gogame.server.domain.entities.UserEntity;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserEntity> getUserInfo(Long userId);
    Optional<UserBioEntity> getUserBio(Long userId);
}
