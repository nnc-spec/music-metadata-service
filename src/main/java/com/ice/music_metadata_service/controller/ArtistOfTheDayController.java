package com.ice.music_metadata_service.controller;

import com.ice.music_metadata_service.dto.ArtistResponse;
import com.ice.music_metadata_service.service.ArtistOfTheDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist-of-the-day")
public class ArtistOfTheDayController {

  private final ArtistOfTheDayService artistOfTheDayService;

  public ArtistOfTheDayController(ArtistOfTheDayService artistOfTheDayService) {
    this.artistOfTheDayService = artistOfTheDayService;
  }

  @GetMapping
  public ResponseEntity<ArtistResponse> getArtistOfTheDay() {
    ArtistResponse artistOfTheDay = artistOfTheDayService.getArtistOfTheDay();
    return ResponseEntity.ok(artistOfTheDay);
  }
}
