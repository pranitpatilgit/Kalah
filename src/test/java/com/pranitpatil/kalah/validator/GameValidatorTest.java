package com.pranitpatil.kalah.validator;

import com.pranitpatil.kalah.database.entity.Game;
import com.pranitpatil.kalah.database.entity.Player;
import com.pranitpatil.kalah.exception.KalahException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameValidatorTest {

    private GameValidator validator;
    private Game game;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void before(){
        validator = new GameValidator();
        game = new Game(1,6);
    }

    @Test
    public void testValidateGameOver(){
        game.setGameOver(true);

        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Game Over.");

        validator.validate(game, 1);
    }

    @Test
    public void testValidatePitId(){
        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Invalid pit id");

        validator.validate(game, 15);

        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Invalid pit id");

        validator.validate(game, -1);
    }

    @Test
    public void testValidateWrongPlayer(){
        game.setCurrentChance(Player.PLAYER_ONE);

        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Wrong player.");

        validator.validate(game, 10);
    }

    @Test
    public void testValidatePitIdHouse(){
        game.setCurrentChance(Player.PLAYER_ONE);

        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Pit id can not be house.");

        validator.validate(game, 7);

        game.setCurrentChance(Player.PLAYER_TWO);
        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Pit id can not be house.");

        validator.validate(game, 14);
    }

    @Test
    public void testValidateNoStones(){
        game.getGameData().setPitValue(0,0);
        game.setCurrentChance(Player.PLAYER_ONE);

        expectedEx.expect(KalahException.class);
        expectedEx.expectMessage("Pit contains 0 stones, choose another pit.");

        validator.validate(game, 1);
    }
}
