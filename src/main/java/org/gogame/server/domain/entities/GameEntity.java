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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
    @SequenceGenerator(name = "game_id_seq", allocationSize = 1)
    @Column(name = "game_id", nullable = false, unique = true)
    private Long gameId;


    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "user_id")
    private UserEntity winner;

    @ManyToOne
    @JoinColumn(name = "user_black_id", referencedColumnName = "user_id")
    private UserEntity userBlack;

    @ManyToOne
    @JoinColumn(name = "user_white_id", referencedColumnName = "user_id")
    private UserEntity userWhite;
}
