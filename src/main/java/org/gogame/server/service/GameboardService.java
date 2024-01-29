package org.gogame.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gogame.server.domain.entities.GameboardEntity;
import org.gogame.server.domain.entities.GameboardJSON;
import org.gogame.server.domain.entities.enums.StoneTypeEnum;
import org.gogame.server.repositories.GameboardRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameboardService {

    private final GameboardRepository gameboardRepo;
    private final ObjectMapper objectMapper;

    private GameboardJSON readGameboard(Long gameId) {
        try {
            GameboardEntity gameboardEntity = gameboardRepo.findById(gameId).orElseThrow();
            return objectMapper.readValue(gameboardEntity.getGameboard(), GameboardJSON.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveGameboard(Long gameId, GameboardJSON gameboardJSON) {
        try {
            GameboardEntity gameboardEntity = gameboardRepo.findById(gameId).orElseThrow();
            gameboardEntity.setGameboard(objectMapper.writeValueAsString(gameboardJSON));
            gameboardRepo.save(gameboardEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setStone(Long gameId, int x, int y, StoneTypeEnum stoneType) {
        GameboardJSON gameboardJSON = readGameboard(gameId);
        gameboardJSON.setStone(x, y, stoneType);
        saveGameboard(gameId, gameboardJSON);
    }

    public Character getStone(Long gameId, int x, int y) {
        GameboardJSON gameboardJSON = readGameboard(gameId);
        return gameboardJSON.getStone(x, y);
    }
}
