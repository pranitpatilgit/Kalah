package com.pranitpatil.kalah.database.entity;

import com.pranitpatil.kalah.config.KalahProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class Database {

    private KalahProperties kalahProperties;

    @Autowired
    public Database(KalahProperties kalahProperties) {
        this.kalahProperties = kalahProperties;
    }

    private Map<Integer, Game> games = new HashMap<>();

    //Making It Immutable
    public Game getGame(int id){
        try {
            return (Game) games.get(id).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone Not Supported");
        }
    }

    public Game createGame(){
        Game game = new Game(Math.abs(new Random().nextInt()), kalahProperties.getInitialPitCount());
        games.put(game.getId(), game);
        try {
            return (Game) game.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone Not Supported");
        }
    }

    public void saveOrUpdate(Game game){
        this.games.put(game.getId(), game);
    }

}
