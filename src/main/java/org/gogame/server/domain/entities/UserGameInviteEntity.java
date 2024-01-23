package org.gogame.server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_game_invite")
public class UserGameInviteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_invite_id_seq")
    @SequenceGenerator(name = "game_invite_id_seq", allocationSize = 1)
    @Column(name = "game_invite_id", nullable = false, unique = true)
    private Long gameInviteId;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_sender_id")
    private UserEntity userSender;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_receiver_id")
    private UserEntity userReceiver;
}
