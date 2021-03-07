package com.pranitpatil.kalah.controller;

import com.pranitpatil.kalah.database.entity.Game;
import com.pranitpatil.kalah.dto.CreateGameResponse;
import com.pranitpatil.kalah.dto.GameDto;
import com.pranitpatil.kalah.service.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;

    private GameDto gameDto;

    @Before
    public void before(){
        gameController = new GameController(gameService);
        gameDto = new GameDto(new Game(1, 6));
    }

    @Test
    public void testCreateGame(){
        when(gameService.createGame()).thenReturn(gameDto);

        CreateGameResponse response = gameController.createGame();

        Assert.assertEquals(1, response.getId());
        Assert.assertNotNull(response.getLinks());
    }

    @Test
    public void testMoveStone(){
        when(gameService.move(1, 1)).thenReturn(gameDto);

        GameDto response = gameController.moveStone(1, 1);

        Assert.assertEquals(1, response.getId());
        Assert.assertNotNull(response.getLinks());
    }

    @Test
    public void testGetGame() {
        when(gameService.getGame(1)).thenReturn(gameDto);

        GameDto response = gameController.getGame(1);

        Assert.assertEquals(1, response.getId());
        Assert.assertNotNull(response.getLinks());
    }
}
