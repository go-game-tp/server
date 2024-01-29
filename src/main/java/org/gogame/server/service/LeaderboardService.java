package org.gogame.server.service;

import lombok.RequiredArgsConstructor;
import org.gogame.server.domain.entities.LeaderboardEntity;
import org.gogame.server.domain.entities.UserBioEntity;
import org.gogame.server.domain.entities.UserEntity;
import org.gogame.server.domain.entities.dto.user.UserProfileDto;
import org.gogame.server.repositories.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final UserRepository userRepo;
    private final UserBioRepository userBioRepo;
    private final UserStatsRepository userStatsRepo;
    private final LeaderboardRepository leaderboardRepo;
    private final UserFriendshipRepository userFriendshipRepo;

    public List<UserProfileDto> getLeaderboard(Long userId) throws SQLException {
        List<UserProfileDto> ret = new ArrayList<>();

        for (var leaderboardEntity : leaderboardRepo.findAll()) {

            var currentUserId = leaderboardEntity.getUserId();

            var score = leaderboardEntity.getScore();

            var userBioEntity = userBioRepo.findById(currentUserId);
            if (userBioEntity.isEmpty()) {
                throw new SQLException("User doesn't have bio");
            }
            var bio = userBioEntity.get().getBio();

            var userEntity = userRepo.findById(currentUserId);
            if (userEntity.isEmpty()) {
                throw new SQLException("User doesn't exist");
            }
            var nickname = userEntity.get().getNickname();

            var userStatsEntity = userStatsRepo.findById(currentUserId);
            if (userStatsEntity.isEmpty()) {
                throw new SQLException("User doesn't have stats");
            }
            var userStats = userStatsEntity.get();
            var winsPerLosses = userStats.getGameWon().floatValue() / userStats.getGameLost().floatValue();

            var friendshipEntity = userFriendshipRepo.findByUserIds(userId, currentUserId);

            var isFriend = friendshipEntity.isPresent();

            ret.add(
                    UserProfileDto.builder()
                            .nickname(nickname)
                            .bio(bio)
                            .score(score)
                            .winsPerLosses(winsPerLosses)
                            .isFriend(isFriend)
                            .build()
            );
        }

        return ret;
    }

}
