package org.gogame.server.repositories;

import org.gogame.server.domain.entities.*;
import org.springframework.data.util.Pair;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestData {
    static class UserEntityUtils {

        public static UserEntity createA() {
            return UserEntity.builder()
                    .userId(1L)
                    .nickname("romka")
                    .passwordHash("qwertyuiop")
                    .email("romka@romka.romka")
                    .joinDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 18, 33, 11)))
                    .build();
        }

        public static UserEntity createB() {
            return UserEntity.builder()
                    .userId(2L)
                    .nickname("romka2")
                    .passwordHash("qwertyuiop2")
                    .email("romka2@romka.romka")
                    .joinDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 18, 33, 12)))
                    .build();
        }

        public static UserEntity createC() {
            return UserEntity.builder()
                    .userId(3L)
                    .nickname("romka3")
                    .passwordHash("qwertyuiop3")
                    .email("romka3@romka.romka")
                    .joinDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 18, 33, 12)))
                    .build();
        }
    }

    static class GameEntityUtils {

        public static Pair<GameEntity, GameEntity> createA(final UserRepository userRepo) {
            var userA = UserEntityUtils.createA();
            var userB = UserEntityUtils.createB();

            if (userRepo.findById(userA.getUserId()).isEmpty()) {
                userRepo.save(userA);
            }
            if (userRepo.findById(userB.getUserId()).isEmpty()) {
                userRepo.save(userB);
            }

            var gameId = 1L;

            return Pair.of(
                    GameEntity.builder()
                            .gameId(gameId)
                            .userId(userA.getUserId())
                            .userColor(UserColor.BLACK)
                            .result(GameResult.WIN)
                            .build(),
                    GameEntity.builder()
                            .gameId(gameId)
                            .userId(userB.getUserId())
                            .userColor(UserColor.WHITE)
                            .result(GameResult.LOSS)
                            .build()
            );
        }

        public static Pair<GameEntity, GameEntity> createB(final UserRepository userRepo) {
            var userA = UserEntityUtils.createA();
            var userB = UserEntityUtils.createB();

            if (userRepo.findById(userA.getUserId()).isEmpty()) {
                userRepo.save(userA);
            }
            if (userRepo.findById(userB.getUserId()).isEmpty()) {
                userRepo.save(userB);
            }

            var gameId = 2L;

            return Pair.of(
                    GameEntity.builder()
                            .gameId(gameId)
                            .userId(userA.getUserId())
                            .userColor(UserColor.BLACK)
                            .result(GameResult.LOSS)
                            .build(),
                    GameEntity.builder()
                            .gameId(gameId)
                            .userId(userB.getUserId())
                            .userColor(UserColor.WHITE)
                            .result(GameResult.WIN)
                            .build()
            );
        }

        public static Pair<GameEntity, GameEntity> createC(final UserRepository userRepo) {
            var userB = UserEntityUtils.createB();
            var userC = UserEntityUtils.createC();

            if (userRepo.findById(userB.getUserId()).isEmpty()) {
                userRepo.save(userB);
            }
            if (userRepo.findById(userC.getUserId()).isEmpty()) {
                userRepo.save(userC);
            }

            var gameId = 3L;

            return Pair.of(
                    GameEntity.builder()
                            .gameId(gameId)
                            .userId(userC.getUserId())
                            .userColor(UserColor.BLACK)
                            .result(GameResult.WIN)
                            .build(),
                    GameEntity.builder()
                            .gameId(gameId)
                            .userId(userB.getUserId())
                            .userColor(UserColor.WHITE)
                            .result(GameResult.LOSS)
                            .build()
            );
        }
    }

    public static class GameJournalUtils {

        public static GameJournalEntity createA(final GameRepository gameRepo,
                                                final UserRepository userRepo) {
            var gameEntity = GameEntityUtils.createA(userRepo);
            if (gameRepo.findById(gameEntity.getFirst().getId()).isEmpty()) {
                gameRepo.save(gameEntity.getFirst());
                gameRepo.save(gameEntity.getSecond());
            }

            return GameJournalEntity.builder()
                    .turnId(1L)
                    .gameId(gameEntity.getFirst().getGameId())
                    .action(GameAction.MOVE)
                    .turnX(1)
                    .turnY(2)
                    .turnDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 23, 27, 18)))
                    .build();
        }

        public static GameJournalEntity createB(final GameRepository gameRepo,
                                                final UserRepository userRepo) {
            var gameEntity = GameEntityUtils.createB(userRepo);
            if (gameRepo.findById(gameEntity.getFirst().getId()).isEmpty()) {
                gameRepo.save(gameEntity.getFirst());
                gameRepo.save(gameEntity.getSecond());
            }

            return GameJournalEntity.builder()
                    .gameId(gameEntity.getFirst().getGameId())
                    .action(GameAction.STOP_REQ)
                    .turnDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 23, 27, 18)))
                    .build();
        }

        public static GameJournalEntity createC(final GameRepository gameRepo,
                                                final UserRepository userRepo) {
            var gameEntity = GameEntityUtils.createC(userRepo);
            if (gameRepo.findById(gameEntity.getFirst().getId()).isEmpty()) {
                gameRepo.save(gameEntity.getFirst());
                gameRepo.save(gameEntity.getSecond());
            }

            return GameJournalEntity.builder()
                    .gameId(gameEntity.getFirst().getGameId())
                    .action(GameAction.LEAVE)
                    .turnDate(Timestamp.valueOf(LocalDateTime.of(2024, 1, 12, 23, 27, 18)))
                    .build();
        }
    }

    static class LeaderboardUtils {

        public static LeaderboardEntity createA(final UserRepository userRepo) {

            var userA = UserEntityUtils.createA();

            if (userRepo.findById(userA.getUserId()).isEmpty()) {
                userRepo.save(userA);
            }

            return LeaderboardEntity.builder()
                    .userPos(1L)
                    .user(userA)
                    .score(666L)
                    .build();
        }

        public static LeaderboardEntity createB(final UserRepository userRepo) {

            var userB = UserEntityUtils.createB();

            if (userRepo.findById(userB.getUserId()).isEmpty()) {
                userRepo.save(userB);
            }

            return LeaderboardEntity.builder()
                    .userPos(2L)
                    .user(userB)
                    .score(145L)
                    .build();
        }

        public static LeaderboardEntity createC(final UserRepository userRepo) {

            var userC = UserEntityUtils.createC();

            if (userRepo.findById(userC.getUserId()).isEmpty()) {
                userRepo.save(userC);
            }

            return LeaderboardEntity.builder()
                    .userPos(3L)
                    .user(userC)
                    .score(110L)
                    .build();
        }
    }

    static class MessageUtils {

        public static MessageEntity createA(final GameRepository gameRepo,
                                            final UserRepository userRepo) {

            var gameEntityA = GameEntityUtils.createA(userRepo);

            if (gameRepo.findById(gameEntityA.getFirst().getId()).isEmpty()) {
                gameRepo.save(gameEntityA.getFirst());
                gameRepo.save(gameEntityA.getSecond());
            }

            var userBlackId = gameEntityA.getFirst().getUserColor() == UserColor.BLACK ?
                    gameEntityA.getFirst().getUserId() : gameEntityA.getSecond().getUserId();

            var userBlackOpt = userRepo.findById(userBlackId);

            if (userBlackOpt.isEmpty()) {
                return null;
            }

            var userBlack = userBlackOpt.get();

            return MessageEntity.builder()
                    .messageId(1L)
                    .gameId(gameEntityA.getFirst().getGameId())
                    .author(userBlack)
                    .text("tests are boring")
                    .build();
        }

        public static MessageEntity createB(final GameRepository gameRepo,
                                            final UserRepository userRepo) {

            var gameEntityB = GameEntityUtils.createB(userRepo);

            if (gameRepo.findById(gameEntityB.getFirst().getId()).isEmpty()) {
                gameRepo.save(gameEntityB.getFirst());
                gameRepo.save(gameEntityB.getSecond());
            }

            var userBlackId = gameEntityB.getFirst().getUserColor() == UserColor.BLACK ?
                    gameEntityB.getFirst().getUserId() : gameEntityB.getSecond().getUserId();

            var userBlackOpt = userRepo.findById(userBlackId);

            if (userBlackOpt.isEmpty()) {
                return null;
            }

            var userBlack = userBlackOpt.get();

            return MessageEntity.builder()
                    .messageId(2L)
                    .gameId(gameEntityB.getFirst().getGameId())
                    .author(userBlack)
                    .text("very boring")
                    .build();
        }

        public static MessageEntity createC(final GameRepository gameRepo,
                                            final UserRepository userRepo) {

            var gameEntityC = GameEntityUtils.createC(userRepo);

            if (gameRepo.findById(gameEntityC.getFirst().getId()).isEmpty()) {
                gameRepo.save(gameEntityC.getFirst());
                gameRepo.save(gameEntityC.getSecond());
            }

            var userBlackId = gameEntityC.getFirst().getUserColor() == UserColor.BLACK ?
                    gameEntityC.getFirst().getUserId() : gameEntityC.getSecond().getUserId();

            var userBlackOpt = userRepo.findById(userBlackId);

            if (userBlackOpt.isEmpty()) {
                return null;
            }

            var userBlack = userBlackOpt.get();

            return MessageEntity.builder()
                    .messageId(3L)
                    .gameId(gameEntityC.getFirst().getGameId())
                    .author(userBlack)
                    .text("very very boring")
                    .build();
        }
    }

}