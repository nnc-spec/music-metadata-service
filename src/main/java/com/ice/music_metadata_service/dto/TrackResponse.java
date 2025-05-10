package com.ice.music_metadata_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrackResponse {
    private UUID id;
    private String title;
    private String genre;
    private Integer length;
    private UUID artistId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrackResponse() {
    }

    public TrackResponse(
        UUID id,
        String title,
        String genre,
        Integer length,
        UUID artistId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.artistId = artistId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
