package com.pranitpatil.kalah.validator;

import com.pranitpatil.kalah.KalahUtils;
import com.pranitpatil.kalah.database.entity.Game;
import com.pranitpatil.kalah.database.entity.Player;
import com.pranitpatil.kalah.exception.KalahException;
import org.springframework.stereotype.Component;

@Component
public class GameValidator {

    public void validate(Game game, int pitId){
        if(game.isGameOver()){
            throw new KalahException("Game Over.");
        }

        if(pitId < 1 || pitId > 14){
            throw new KalahException("Invalid pit id");
        }

        Player currentPlayer = KalahUtils.getOwner(pitId - 1);
        if(currentPlayer != game.getCurrentChance()){
            throw new KalahException("Wrong player.");
        }

        if(pitId == KalahUtils.getHouseIndex(Player.PLAYER_ONE) + 1 ||
                pitId == KalahUtils.getHouseIndex(Player.PLAYER_TWO) + 1){
            throw new KalahException("Pit id can not be house.");
        }

        if(pitId > 7 && pitId <= 14 && currentPlayer == Player.PLAYER_ONE){
            throw new KalahException("Pit id does not belong to " + Player.PLAYER_ONE);
        }

        if(pitId > 0 && pitId <= 7 && currentPlayer == Player.PLAYER_TWO){
            throw new KalahException("Pit id does not belong to " + Player.PLAYER_TWO);
        }

    }
}
