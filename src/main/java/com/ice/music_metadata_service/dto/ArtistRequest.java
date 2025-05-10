package com.ice.music_metadata_service.dto;

public class ArtistRequest {
    private String name;

    public ArtistRequest() {
    }

    public ArtistRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
