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

    // TODO how do we specify both of these as the foreign key?
    @ManyToOne
    @JoinColumn(name = "user_sender_id", referencedColumnName = "user_id")
    private UserEntity userSenderId;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id", referencedColumnName = "user_id")
    private UserEntity userReceiverId;
}
