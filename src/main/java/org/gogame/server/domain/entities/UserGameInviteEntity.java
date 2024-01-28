package org.gogame.server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_game_invite")
public class UserGameInviteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "game_invite_id", nullable = false, unique = true)
    private Long gameInviteId;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserEntity userSender;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserEntity userReceiver;
}
