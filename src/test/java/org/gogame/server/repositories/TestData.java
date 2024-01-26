package org.gogame.server.repositories;

import org.gogame.server.domain.entities.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestData {
    static class UserEntityUtils {

        public static UserEntity createA() {
            return UserEntity.builder()
                    .nickname("romka")
                    .passwordHash("qwertyuiop")
                    .email("romka@romka.romka")
                    .joinDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 18, 33, 11)))
                    .build();
        }

        public static UserEntity createB() {
            return UserEntity.builder()
                    .nickname("romka2")
                    .passwordHash("qwertyuiop2")
                    .email("romka2@romka.romka")
                    .joinDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 18, 33, 12)))
                    .build();
        }

        public static UserEntity createC() {
            return UserEntity.builder()
                    .nickname("romka3")
                    .passwordHash("qwertyuiop3")
                    .email("romka3@romka.romka")
                    .joinDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 18, 33, 12)))
                    .build();
        }
    }

    static class GameEntityUtils {

        public static GameEntity createA(final UserRepository userRepo) {
            var userW = UserEntityUtils.createA();
            var userB = UserEntityUtils.createB();

            if (userRepo.findByNickname(userW.getNickname()).isEmpty()) {
                userRepo.save(userW);
            } else {
                userW = userRepo.findByNickname(userW.getNickname()).get();
            }

            if (userRepo.findByNickname(userB.getNickname()).isEmpty()) {
                userRepo.save(userB);
            } else {
                userB = userRepo.findByNickname(userB.getNickname()).get();
            }

            return GameEntity.builder()
                    .userWhite(userW)
                    .userBlack(userB)
                    .winner(userW)
                    .build();
        }

        public static GameEntity createB(final UserRepository userRepo) {
            var userW = UserEntityUtils.createA();
            var userB = UserEntityUtils.createB();

            if (userRepo.findByNickname(userW.getNickname()).isEmpty()) {
                userRepo.save(userW);
            } else {
                userW = userRepo.findByNickname(userW.getNickname()).get();
            }

            if (userRepo.findByNickname(userB.getNickname()).isEmpty()) {
                userRepo.save(userB);
            } else {
                userB = userRepo.findByNickname(userB.getNickname()).get();
            }

            return GameEntity.builder()
                    .userWhite(userW)
                    .userBlack(userB)
                    .winner(userW)
                    .build();
        }

        public static GameEntity createC(final UserRepository userRepo) {
            var userW = UserEntityUtils.createB();
            var userB = UserEntityUtils.createC();

            if (userRepo.findByNickname(userW.getNickname()).isEmpty()) {
                userRepo.save(userW);
            } else {
                userW = userRepo.findByNickname(userW.getNickname()).get();
            }

            if (userRepo.findByNickname(userB.getNickname()).isEmpty()) {
                userRepo.save(userB);
            } else {
                userB = userRepo.findByNickname(userB.getNickname()).get();
            }

            return GameEntity.builder()
                    .userWhite(userW)
                    .userBlack(userB)
                    .winner(userB)
                    .build();
        }
    }

    public static class GameJournalUtils {

        public static GameJournalEntity createA(final GameRepository gameRepo,
                                                final UserRepository userRepo) {
            var gameEntity = GameEntityUtils.createA(userRepo);
            gameRepo.save(gameEntity);

            return GameJournalEntity.builder()
                    .turnId(1L)
                    .game(gameEntity)
                    .action(GameAction.MOVE)
                    .author(gameEntity.getUserWhite())
                    .turnX(1)
                    .turnY(2)
                    .turnDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 23, 27, 18)))
                    .build();
        }

        public static GameJournalEntity createB(final GameRepository gameRepo,
                                                final UserRepository userRepo) {
            var gameEntity = GameEntityUtils.createB(userRepo);
            gameRepo.save(gameEntity);

            return GameJournalEntity.builder()
                    .game(gameEntity)
                    .action(GameAction.STOP_REQ)
                    .author(gameEntity.getUserBlack())
                    .turnDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 23, 27, 18)))
                    .build();
        }

        public static GameJournalEntity createC(final GameRepository gameRepo,
                                                final UserRepository userRepo) {
            var gameEntity = GameEntityUtils.createC(userRepo);
            gameRepo.save(gameEntity);

            return GameJournalEntity.builder()
                    .game(gameEntity)
                    .action(GameAction.LEAVE)
                    .author(gameEntity.getUserWhite())
                    .turnDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 23, 27, 18)))
                    .build();
        }
    }

    static class LeaderboardUtils {

        public static LeaderboardEntity createA(final UserRepository userRepo) {

            var userA = UserEntityUtils.createA();

            if (userRepo.findByNickname(userA.getNickname()).isEmpty()) {
                userRepo.save(userA);
            }

            return LeaderboardEntity.builder()
                    .user(userA)
                    .score(666L)
                    .build();
        }

        public static LeaderboardEntity createB(final UserRepository userRepo) {

            var userB = UserEntityUtils.createB();

            if (userRepo.findByNickname(userB.getNickname()).isEmpty()) {
                userRepo.save(userB);
            }

            return LeaderboardEntity.builder()
                    .user(userB)
                    .score(145L)
                    .build();
        }

        public static LeaderboardEntity createC(final UserRepository userRepo) {

            var userC = UserEntityUtils.createC();

            if (userRepo.findByNickname(userC.getNickname()).isEmpty()) {
                userRepo.save(userC);
            }

            return LeaderboardEntity.builder()
                    .user(userC)
                    .score(110L)
                    .build();
        }
    }

    static class MessageUtils {

        public static MessageEntity createA(final GameRepository gameRepo,
                                            final UserRepository userRepo) {

            var gameEntity = GameEntityUtils.createA(userRepo);
            gameRepo.save(gameEntity);

            return MessageEntity.builder()
                    .game(gameEntity)
                    .author(gameEntity.getUserWhite())
                    .text("tests are boring")
                    .build();
        }

        public static MessageEntity createB(final GameRepository gameRepo,
                                            final UserRepository userRepo) {

            var gameEntity = GameEntityUtils.createB(userRepo);
            gameRepo.save(gameEntity);

            return MessageEntity.builder()
                    .game(gameEntity)
                    .author(gameEntity.getUserWhite())
                    .text("very boring")
                    .build();
        }

        public static MessageEntity createC(final GameRepository gameRepo,
                                            final UserRepository userRepo) {

            var gameEntity = GameEntityUtils.createC(userRepo);
            gameRepo.save(gameEntity);

            return MessageEntity.builder()
                    .game(gameEntity)
                    .author(gameEntity.getUserWhite())
                    .text("very very boring")
                    .build();
        }
    }
}