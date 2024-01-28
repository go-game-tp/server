package org.gogame.server.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

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

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "invite_status", length = 9, nullable = false)
    private UserInviteStatus status = UserInviteStatus.PENDING;

    @Builder.Default
    @Column(name = "game_id")
    private Long gameId = -1L;
}
