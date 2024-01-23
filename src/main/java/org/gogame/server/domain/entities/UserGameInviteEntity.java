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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_game_invite_id_seq")
    @SequenceGenerator(name = "user_game_invite_id_seq", allocationSize = 1)
    @Column(name = "user_game_invite_id", nullable = false, unique = true)
    private Long userPlayInviteId;

    @ManyToOne
    @JoinColumn(name = "user_sender_id", referencedColumnName = "user_id")
    private UserEntity userSenderId;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id", referencedColumnName = "user_id")
    private UserEntity userReceiverId;
}
