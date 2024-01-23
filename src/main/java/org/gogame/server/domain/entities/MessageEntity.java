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
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turn_id_seq")
    @SequenceGenerator(name = "turn_id_seq", allocationSize = 1)
    @Column(name = "message_id", nullable = false, unique = true)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private UserEntity author;

    @Column(nullable = false, length = 2048)
    private String text;
}
