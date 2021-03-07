package com.pranitpatil.kalah.service;

import com.pranitpatil.kalah.database.entity.Game;
import com.pranitpatil.kalah.dto.GameDto;

public interface GameService {

    GameDto createGame();

    GameDto move(int gameId, int pitId);

    boolean checkGameOver(Game game);

    void stopGame(Game game);
}
