package com.pranitpatil.kalah.dto;

import com.pranitpatil.kalah.database.entity.Game;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Map;

public class GameDto extends RepresentationModel<GameDto> implements Serializable {

    private int id;
    private Map<Integer, String> status;

    public GameDto(Game game) {
        this.id = game.getId();
        this.status = game.getGameData().getPitsAsMap();
    }

    public int getId() {
        return id;
    }

    public Map<Integer, String> getStatus() {
        return status;
    }
}
