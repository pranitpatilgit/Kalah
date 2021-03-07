package com.pranitpatil.kalah.controller;

import com.pranitpatil.kalah.dto.CreateGameResponse;
import com.pranitpatil.kalah.dto.GameDto;
import com.pranitpatil.kalah.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create a new game.")
    public CreateGameResponse createGame(){
        CreateGameResponse createGameResponse = new CreateGameResponse(gameService.createGame().getId());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class)
            .getGame(createGameResponse.getId())).withSelfRel();
        createGameResponse.add(selfLink);
        return createGameResponse;
    }

    @PutMapping("{gameId}/pits/{pitId}")
    @Operation(summary = "This is the request for a player to make a move and pick up stones from one of their pit and sow it to the next pits.")
    public GameDto moveStone(@PathVariable int gameId, @PathVariable int pitId){
        GameDto game = gameService.move(gameId, pitId);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class)
                .getGame(game.getId())).withSelfRel();
        game.add(selfLink);

        return game;
    }

    @GetMapping("{gameId}")
    @Operation(summary = "Gets the details of the game and the position and count of the stone in the pit")
    public GameDto getGame(@PathVariable int gameId){
        GameDto game = gameService.getGame(gameId);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class)
                .getGame(game.getId())).withSelfRel();
        game.add(selfLink);

        return game;
    }
}
