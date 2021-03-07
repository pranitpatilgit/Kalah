package com.pranitpatil.kalah.service;

import com.pranitpatil.kalah.KalahUtils;
import com.pranitpatil.kalah.database.entity.Database;
import com.pranitpatil.kalah.database.entity.Game;
import com.pranitpatil.kalah.database.entity.GameData;
import com.pranitpatil.kalah.database.entity.Player;
import com.pranitpatil.kalah.dto.GameDto;
import com.pranitpatil.kalah.validator.GameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private Database database;
    private GameValidator gameValidator;

    @Autowired
    public GameServiceImpl(Database database, GameValidator gameValidator) {
        this.database = database;
        this.gameValidator = gameValidator;
    }

    @Override
    public GameDto createGame() {
        return new GameDto(database.createGame());
    }

    @Override
    public GameDto move(int gameId, int pitId) {
        //Convert Pit to array index and get the next Pit
        int currentPitIndex = pitId - 1;

        Game game = database.getGame(gameId);
        Player currentPlayer = getCurrentPlayer(game, currentPitIndex);

        gameValidator.validate(game, pitId);

        int lastPitIndex = moveStones(currentPitIndex, game, currentPlayer);

        game.setCurrentChance(getNextPlayer(lastPitIndex, currentPlayer));

        if(!KalahUtils.isHouse(lastPitIndex) && isHit(lastPitIndex, currentPlayer, game)){
            transferStonesToHouse(game, lastPitIndex, currentPlayer);
        }

        if(checkGameOver(game)){
            stopGame(game);
        }

        database.saveOrUpdate(game);
        return new GameDto(game);
    }

    private int moveStones(int currentPitIndex, Game game, Player currentPlayer) {
        int numberOfStone = game.getGameData().getPitValue(currentPitIndex);
        //Set Current pit count to Zero
        game.getGameData().setPitValue(currentPitIndex, 0);
        currentPitIndex = nextPitIndex(currentPitIndex);
        int lastPitIndex = currentPitIndex;

        while (numberOfStone > 0){
            lastPitIndex = currentPitIndex;
            if(KalahUtils.isHouse(currentPitIndex) && currentPlayer != KalahUtils.getOwner(currentPitIndex)){
                currentPitIndex = nextPitIndex(currentPitIndex);
                continue;
            }
            else {
                numberOfStone--;
                game.getGameData().addStonesToPit(currentPitIndex, 1);
                currentPitIndex = nextPitIndex(currentPitIndex);
            }
        }
        return lastPitIndex;
    }

    private Player getCurrentPlayer(Game game, int pitIndex) {
        if(game.getCurrentChance() == null){
            game.setCurrentChance(KalahUtils.getOwner(pitIndex));
        }

        return game.getCurrentChance();
    }

    private int nextPitIndex(int pitIndex){
        return (pitIndex + 1) % 14;
    }

    private Player getNextPlayer(int lastPitIndex, Player currentPlayer){
        if(KalahUtils.isHouse(lastPitIndex) && currentPlayer == KalahUtils.getOwner(lastPitIndex)){
            return currentPlayer;
        }
        else {
            return currentPlayer == Player.PLAYER_ONE
                    ? Player.PLAYER_TWO
                    : Player.PLAYER_ONE;
        }
    }

    private boolean isHit(int lastPitIndex, Player player, Game game){
        if(game.getGameData().getPitValue(lastPitIndex) == 1 &&
                player == KalahUtils.getOwner(lastPitIndex) &&
                game.getGameData().getPitValue(KalahUtils.getOppositePitIndex(lastPitIndex)) > 0){
            return true;
        }

        return false;
    }

    private void transferStonesToHouse(Game game, int lastPitIndex, Player player){
        int stoneCount = game.getGameData().getPitValue(KalahUtils.getOppositePitIndex(lastPitIndex));

        logger.info("Its a hit for {} and gets {} stones in the house.", player, stoneCount+1);

        int playerHouseIndex = KalahUtils.getHouseIndex(player);
        game.getGameData().addStonesToPit(playerHouseIndex, stoneCount + 1);

        game.getGameData().setPitValue(lastPitIndex, 0);
        game.getGameData().setPitValue(KalahUtils.getOppositePitIndex(lastPitIndex), 0);
    }

    @Override
    public boolean checkGameOver(Game game) {
        GameData gameData = game.getGameData();

        if(gameData.totalStoneCount(true, Player.PLAYER_ONE) == 0 ||
                gameData.totalStoneCount(true, Player.PLAYER_TWO) == 0){
            return true;
        }

        return false;
    }

    @Override
    public void stopGame(Game game) {
        game.setGameOver(true);

        int playerOneStoneCount = game.getGameData().totalStoneCount(false, Player.PLAYER_ONE);
        int playerTwoStoneCount = game.getGameData().totalStoneCount(false, Player.PLAYER_TWO);

        for (int i = 0; i < 14; i++) {
            game.getGameData().setPitValue(i, 0);
        }

        game.getGameData().setPitValue(KalahUtils.getHouseIndex(Player.PLAYER_ONE), playerOneStoneCount);
        game.getGameData().setPitValue(KalahUtils.getHouseIndex(Player.PLAYER_TWO), playerTwoStoneCount);
    }

}
