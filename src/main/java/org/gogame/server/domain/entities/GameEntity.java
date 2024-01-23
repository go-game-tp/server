package org.gogame.server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    // TODO figure out id generation for this
    @Column(name = "game_id", nullable = false, unique = true)
    private Long gameId;

    @ManyToMany
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(name = "user_color", nullable = false)
    private UserColor userColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_result", length = 5)
    private GameResult result;
}
