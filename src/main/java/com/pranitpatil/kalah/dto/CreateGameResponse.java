package com.pranitpatil.kalah.dto;

import org.springframework.hateoas.RepresentationModel;

public class CreateGameResponse extends RepresentationModel<CreateGameResponse> {

    int id;

    public CreateGameResponse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
