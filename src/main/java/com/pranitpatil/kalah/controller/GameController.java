package com.pranitpatil.kalah.controller;

import com.pranitpatil.kalah.dto.CreateGameResponse;
import com.pranitpatil.kalah.dto.GameDto;
import com.pranitpatil.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
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
    public CreateGameResponse createGame(){
        CreateGameResponse createGameResponse = new CreateGameResponse(gameService.createGame().getId());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class)
            .getGame(createGameResponse.getId())).withSelfRel();
        createGameResponse.add(selfLink);
        return createGameResponse;
    }

    @PutMapping("{gameId}/pits/{pitId}")
    public GameDto moveStone(@PathVariable int gameId, @PathVariable int pitId){
        GameDto game = gameService.move(gameId, pitId);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class)
                .getGame(game.getId())).withSelfRel();
        game.add(selfLink);

        return game;
    }

    @GetMapping("{gameId}")
    public GameDto getGame(@PathVariable int gameId){
        GameDto game = gameService.getGame(gameId);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class)
                .getGame(game.getId())).withSelfRel();
        game.add(selfLink);

        return game;
    }
}
