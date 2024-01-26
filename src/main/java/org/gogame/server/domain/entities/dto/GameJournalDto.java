package org.gogame.server.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gogame.server.domain.entities.GameAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameJournalDto {

    private Long gameId;

    private Integer turnX;

    private Integer turnY;

    private GameAction action;
}
