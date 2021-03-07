package com.pranitpatil.kalah.database.entity;

import com.pranitpatil.kalah.config.KalahProperties;
import com.pranitpatil.kalah.exception.KalahException;
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

    /**
     * Store for all running or over games
     */
    private Map<Integer, Game> games = new HashMap<>();

    //Making It Immutable
    /**
     * Returns clone of the game from database.
     * All updates fields can be saved by using saveOrUpdate method.
     * @param id
     * @return Game
     */
    public Game getGame(int id){
        try {
            if(games.get(id) == null){
              throw new KalahException("Game not found.");
            }

            return (Game) games.get(id).clone();
        } catch (CloneNotSupportedException e) {
            throw new KalahException(e);
        }
    }

    public Game createGame(){
        Game game = new Game(Math.abs(new Random().nextInt()), kalahProperties.getInitialPitCount());
        games.put(game.getId(), game);
        try {
            return (Game) game.clone();
        } catch (CloneNotSupportedException e) {
            throw new KalahException(e);
        }
    }

    /**
     * All updates fields of Game or GameData can be saved by using saveOrUpdate method.
     * This is required as the creation and get methods gives a clone object.
     * @param game
     */
    public void saveOrUpdate(Game game){
        this.games.put(game.getId(), game);
    }

}
