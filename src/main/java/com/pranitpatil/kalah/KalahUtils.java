package com.pranitpatil.kalah;

import com.pranitpatil.kalah.database.entity.Player;

public class KalahUtils {

    public static boolean isHouse(int pitIndex){
        if(pitIndex == 6 || pitIndex == 13)
            return true;
        else
            return false;
    }

    public static Player getOwner(int pitIndex){
        if(pitIndex < 7)
            return Player.PLAYER_ONE;
        else
            return Player.PLAYER_TWO;
    }

    public static int getHouseIndex(Player player){
        return player == Player.PLAYER_ONE ? 6 : 13;
    }

    public static int getOppositePitIndex(int pit){
        return 12 - pit;
    }
}
