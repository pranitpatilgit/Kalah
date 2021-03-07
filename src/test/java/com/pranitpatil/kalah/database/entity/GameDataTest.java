package com.pranitpatil.kalah.database.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameDataTest {

    private GameData gameData;

    @Before
    public void before(){
        gameData = new GameData(6);
    }

    @Test
    public void totalStoneCountTest(){

        Assert.assertEquals(36, gameData.totalStoneCount(true, Player.PLAYER_ONE));
        Assert.assertEquals(36, gameData.totalStoneCount(true, Player.PLAYER_TWO));

        /*
           29 | 3 | 0 | 1 | 0 | 1 | 2 |
              |                       |
              | 4 | 6 | 0 | 0 | 0 | 6 | 20
         */
        setGameData();
        Assert.assertEquals(16, gameData.totalStoneCount(true, Player.PLAYER_ONE));
        Assert.assertEquals(36, gameData.totalStoneCount(false, Player.PLAYER_ONE));
        Assert.assertEquals(7, gameData.totalStoneCount(true, Player.PLAYER_TWO));
        Assert.assertEquals(36, gameData.totalStoneCount(false, Player.PLAYER_TWO));

        /*
           29 | 3 | 0 | 1 | 0 | 1 | 1 |
              |                       |
              | 0 | 0 | 0 | 0 | 0 | 0 | 37
         */
        setGameDataForGameOver();
        Assert.assertEquals(0, gameData.totalStoneCount(true, Player.PLAYER_ONE));
        Assert.assertEquals(37, gameData.totalStoneCount(false, Player.PLAYER_ONE));
        Assert.assertEquals(6, gameData.totalStoneCount(true, Player.PLAYER_TWO));
        Assert.assertEquals(35, gameData.totalStoneCount(false, Player.PLAYER_TWO));
    }

    private void setGameDataForGameOver(){
        gameData.setPitValue(0,0);
        gameData.setPitValue(1,0);
        gameData.setPitValue(2,0);
        gameData.setPitValue(3,0);
        gameData.setPitValue(4,0);
        gameData.setPitValue(5,0);
        gameData.setPitValue(6,37);
        gameData.setPitValue(7,1);
        gameData.setPitValue(8,1);
        gameData.setPitValue(9,0);
        gameData.setPitValue(10,1);
        gameData.setPitValue(11,0);
        gameData.setPitValue(12,3);
        gameData.setPitValue(13,29);
    }

    private void setGameData(){
        gameData.setPitValue(0,4);
        gameData.setPitValue(1,6);
        gameData.setPitValue(2,0);
        gameData.setPitValue(3,0);
        gameData.setPitValue(4,0);
        gameData.setPitValue(5,6);
        gameData.setPitValue(6,20);
        gameData.setPitValue(7,2);
        gameData.setPitValue(8,1);
        gameData.setPitValue(9,0);
        gameData.setPitValue(10,1);
        gameData.setPitValue(11,0);
        gameData.setPitValue(12,3);
        gameData.setPitValue(13,29);
    }
}
