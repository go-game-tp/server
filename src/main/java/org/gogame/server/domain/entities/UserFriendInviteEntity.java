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

    // TODO how do we specify both of these as the foreign key?
    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_sender_id")
    private UserEntity userSender;

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_receiver_id")
    private UserEntity userReceiver;
}
