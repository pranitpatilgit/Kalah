package com.pranitpatil.kalah;

import com.pranitpatil.kalah.database.entity.Player;

public class KalahUtils {

    /**
     * Return if current index is house of one of the player
     * @param pitIndex
     * @return boolean
     */
    public static boolean isHouse(int pitIndex){
        if(pitIndex == 6 || pitIndex == 13)
            return true;
        else
            return false;
    }

    /**
     * Gets the owner of pit.
     * @param pitIndex
     * @return
     */
    public static Player getOwner(int pitIndex){
        if(pitIndex < 7)
            return Player.PLAYER_ONE;
        else
            return Player.PLAYER_TWO;
    }

    public static int getHouseIndex(Player player){
        return player == Player.PLAYER_ONE ? 6 : 13;
    }

    /**
     * Return index of the visually opposite pit
     * 13 | 12 | 11 | 10 |  9 |  8 |  7 |
     *    |                             |
     *    |  0 | 1  |  2 |  3 |  4 |  5 | 6
     * @param pit
     * @return
     */
    public static int getOppositePitIndex(int pit){
        return 12 - pit;
    }
}
