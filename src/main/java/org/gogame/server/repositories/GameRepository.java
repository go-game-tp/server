package org.gogame.server.repositories;

import org.gogame.server.domain.entities.GameEntity;
import org.gogame.server.domain.entities.UserGameInviteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends CrudRepository<GameEntity, Long> {
    @Query(value = """
            SELECT DISTINCT g
            FROM GameEntity g
            WHERE (
                (g.userWhite.userId = :user_a_id AND g.userBlack.userId = :user_b_id)
                OR (g.userWhite.userId = :user_b_id AND g.userBlack.userId = :user_a_id)
            )
            AND g.winner.userId IS NULL
            """)
    GameEntity findCurrentGame(@Param("user_a_id") Long userAId, @Param("user_b_id") Long userBId);
}
