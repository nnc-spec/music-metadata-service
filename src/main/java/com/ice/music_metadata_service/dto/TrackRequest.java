package com.ice.music_metadata_service.dto;

import java.util.UUID;

public class TrackRequest {
  private UUID artistId;
  private String title;
  private String genre;
  private Integer length;

  public TrackRequest() {}

  public TrackRequest(UUID artistId, String title, String genre, Integer length) {
    this.artistId = artistId;
    this.title = title;
    this.genre = genre;
    this.length = length;
  }

  public UUID getArtistId() {
    return artistId;
  }

  public void setArtistId(UUID artistId) {
    this.artistId = artistId;
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
}
