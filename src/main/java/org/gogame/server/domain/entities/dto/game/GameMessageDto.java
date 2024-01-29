package org.gogame.server.domain.entities.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameMessageDto {

    private Long gameId;

    private Long authorId;

    private String content;

    private String timestamp;
}
