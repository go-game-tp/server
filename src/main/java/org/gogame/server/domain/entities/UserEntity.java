package org.gogame.server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(length = 64, nullable = false, unique = true)
    private String nickname;

    @Column(name = "password_hash", length = 64, nullable = false)
    private String passwordHash;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(name = "join_date")
    private Timestamp joinDate;
}
