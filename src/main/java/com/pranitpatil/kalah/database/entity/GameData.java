package com.pranitpatil.kalah.database.entity;

import com.pranitpatil.kalah.KalahUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class GameData implements Cloneable{

    public GameData(int initialPitCount) {
        initializeGameData(initialPitCount);
    }

    private int[] pits = new int[14];

    public void setPits(int[] pits) {
        this.pits = pits;
    }

    private void initializeGameData(int initialPitCount){
        for(int i=0; i < pits.length; i++){
            if(!KalahUtils.isHouse(i)){
                pits[i] = initialPitCount;
            }
        }
    }

    public int getPitValue(int pitIndex){
        return pits[pitIndex];
    }

    public void setPitValue(int pitIndex, int value){
        pits[pitIndex] = value;
    }

    public void addStonesToPit(int pitIndex, int stoneCount){
        pits[pitIndex] = pits[pitIndex] + stoneCount;
    }

    /**
     * This gives user representation of pits from the underlying array based implementation.
     * @return
     */
    public Map<Integer, String> getPitsAsMap(){
        Map<Integer, String> map = new TreeMap<>();

        for (int i = 0; i < pits.length; i++) {
            map.put(i+1, String.valueOf(pits[i]));
        }

        return map;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @param excludeHouse Excludes the stones in house
     * @param player
     * @return Total stone count for a player
     */
    public int totalStoneCount(boolean excludeHouse, Player player){
        if(player == Player.PLAYER_ONE){
            int lastIndex = excludeHouse ? 6 : 7;
            return Arrays.stream(pits, 0, lastIndex)
                    .sum();
        }
        else {
            int lastIndex = excludeHouse ? 12 : 13;
            return Arrays.stream(pits, 7, lastIndex+1)
                    .sum();
        }
    }
}
