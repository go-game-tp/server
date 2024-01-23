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
@Table(name = "user_friendship")
public class UserFriendshipEntity {

    // TODO how do we specify both of these as the foreign key?
    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_a_id")
    private UserEntity userA;

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_b_id")
    private UserEntity userB;
}
