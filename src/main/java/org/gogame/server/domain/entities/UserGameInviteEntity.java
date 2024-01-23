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
