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
@Table(name = "user_lobby")
public class UserLobbyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_lobby_id_seq")
    @SequenceGenerator(name = "user_lobby_id_seq", allocationSize = 1)
    @Column(name = "user_lobby_id", nullable = false, unique = true)
    private Long userLobbyId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_lobby_state", length = 10, nullable = false)
    private UserLobbyState userLobbyState;
}
