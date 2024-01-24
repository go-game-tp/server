package org.gogame.server.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_friend_invite")
public class UserFriendInviteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "friend_invite_id", nullable = false, unique = true)
    private Long friendInviteId;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_sender_id")
    private UserEntity userSender;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_receiver_id")
    private UserEntity userReceiver;
}
