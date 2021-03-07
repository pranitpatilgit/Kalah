package com.pranitpatil.kalah.service;

import com.pranitpatil.kalah.KalahUtils;
import com.pranitpatil.kalah.database.entity.Database;
import com.pranitpatil.kalah.database.entity.Game;
import com.pranitpatil.kalah.database.entity.Player;
import com.pranitpatil.kalah.dto.GameDto;
import com.pranitpatil.kalah.validator.GameValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @Mock
    private Database database;
    @Mock
    private GameValidator gameValidator;

    private GameService gameService;

    @Before
    public void before(){
        gameService = new GameServiceImpl(database, gameValidator);
    }

    @Test
    public void createGameTest(){

    }

    @Test
    public void moveTest(){
        /*
            0 | 6 | 6 | 6 | 6 | 6 | 6 |
              |                       |
              | 6 | 6 | 6 | 6 | 6 | 6 | 0
         */
        Game game = new Game(1, 6);

        when(database.getGame(1)).thenReturn(game);

        /*
            0 | 6 | 6 | 6 | 6 | 6 | 6 |
              |                       |
              | 0 | 7 | 7 | 7 | 7 | 7 | 1
         */
        GameDto gameDto = gameService.move(1, 1);
        Assert.assertEquals(gameDto.getStatus().get(6), "7");
        Assert.assertEquals(gameDto.getStatus().get(KalahUtils.getHouseIndex(Player.PLAYER_ONE) + 1), "1");
        Assert.assertEquals(game.getCurrentChance(), Player.PLAYER_ONE);

        /*
            0 | 7 | 7 | 7 | 7 | 7 | 7 |
              |                       |
              | 0 | 7 | 7 | 7 | 7 | 0 | 2
         */
        gameDto = gameService.move(1, 6);
        Assert.assertEquals(gameDto.getStatus().get(6), "0");
        Assert.assertEquals(gameDto.getStatus().get(12), "7");
        Assert.assertEquals(gameDto.getStatus().get(KalahUtils.getHouseIndex(Player.PLAYER_ONE) + 1), "2");
        Assert.assertEquals(game.getCurrentChance(), Player.PLAYER_TWO);
    }

    @Test
    public void moveTestHit(){

        Game game = new Game(1, 6);
        /*
           29 | 3 | 0 | 1 | 0 | 1 | 2 |
              |                       |
              | 4 | 6 | 0 | 0 | 0 | 6 | 20
         */
        setGameDataForHit(game);

        when(database.getGame(1)).thenReturn(game);

        /*
           29 | 3 | 0 | 1 | 0 | 0 | 2 |
              |                       |
              | 0 | 7 | 1 | 1 | 0 | 6 | 22
         */
        GameDto gameDto = gameService.move(1, 1);
        Assert.assertEquals(gameDto.getStatus().get(5), "0");
        Assert.assertEquals(gameDto.getStatus().get(9), "0");
        Assert.assertEquals(gameDto.getStatus().get(KalahUtils.getHouseIndex(Player.PLAYER_ONE) + 1), "22");
        Assert.assertEquals(game.getCurrentChance(), Player.PLAYER_TWO);
    }

    @Test
    public void moveTestGameOver(){

        Game game = new Game(1, 6);

        /*
           29 | 1 | 0 | 0 | 0 | 0 | 0 |
              |                       |
              | 4 | 6 | 0 | 0 | 6 | 6 | 20
         */
        setGameDataGameOver(game);

        when(database.getGame(1)).thenReturn(game);

        GameDto gameDto = gameService.move(1, 13);
        Assert.assertEquals(gameDto.getStatus().get(5), "0");
        Assert.assertEquals(gameDto.getStatus().get(9), "0");
        Assert.assertEquals(gameDto.getStatus().get(KalahUtils.getHouseIndex(Player.PLAYER_TWO) + 1), "30");
        Assert.assertEquals(gameDto.getStatus().get(KalahUtils.getHouseIndex(Player.PLAYER_ONE) + 1), "42");
        Assert.assertEquals(true, game.isGameOver());
    }

    private void setGameDataForHit(Game game){
        game.getGameData().setPitValue(0,4);
        game.getGameData().setPitValue(1,6);
        game.getGameData().setPitValue(2,0);
        game.getGameData().setPitValue(3,0);
        game.getGameData().setPitValue(4,0);
        game.getGameData().setPitValue(5,6);
        game.getGameData().setPitValue(6,20);
        game.getGameData().setPitValue(7,2);
        game.getGameData().setPitValue(8,1);
        game.getGameData().setPitValue(9,0);
        game.getGameData().setPitValue(10,1);
        game.getGameData().setPitValue(11,0);
        game.getGameData().setPitValue(12,3);
        game.getGameData().setPitValue(13,29);
    }

    private void setGameDataGameOver(Game game){
        game.getGameData().setPitValue(0,4);
        game.getGameData().setPitValue(1,6);
        game.getGameData().setPitValue(2,0);
        game.getGameData().setPitValue(3,0);
        game.getGameData().setPitValue(4,6);
        game.getGameData().setPitValue(5,6);
        game.getGameData().setPitValue(6,20);
        game.getGameData().setPitValue(7,0);
        game.getGameData().setPitValue(8,0);
        game.getGameData().setPitValue(9,0);
        game.getGameData().setPitValue(10,0);
        game.getGameData().setPitValue(11,0);
        game.getGameData().setPitValue(12,1);
        game.getGameData().setPitValue(13,29);
    }
}
