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
    @ManyToOne
    @JoinColumn(name = "user_a_id", referencedColumnName = "user_id")
    private UserEntity userAId;

    @ManyToOne
    @JoinColumn(name = "user_b_id", referencedColumnName = "user_id")
    private UserEntity userBId;
}
