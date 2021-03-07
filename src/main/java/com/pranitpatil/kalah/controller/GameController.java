package com.pranitpatil.kalah.controller;

import com.pranitpatil.kalah.dto.GameDto;
import com.pranitpatil.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "games", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping()
    public ResponseEntity<GameDto> createGame(){
        return ResponseEntity.ok().body(gameService.createGame());
    }

    @PutMapping("{gameId}/pits/{pitId}")
    public ResponseEntity<GameDto> moveStone(@PathVariable int gameId, @PathVariable int pitId){
        return ResponseEntity.ok().body(gameService.move(gameId, pitId));
    }
}
