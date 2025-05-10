package com.ice.music_metadata_service.controller;

import com.ice.music_metadata_service.dto.ArtistRequest;
import com.ice.music_metadata_service.dto.ArtistResponse;
import com.ice.music_metadata_service.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
@Tag(name = "Artist Controller", description = "Artist related operations")
public class ArtistController {
  private final ArtistService artistService;

  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @Operation(summary = "Creates an artist")
  @PostMapping
  public ResponseEntity<ArtistResponse> createArtist(@RequestBody ArtistRequest artistRequest) {
    ArtistResponse artistResponse = artistService.createArtist(artistRequest);
    return new ResponseEntity<>(artistResponse, HttpStatus.CREATED);
  }

  @Operation(summary = "Updates artist name")
  @PutMapping("/{artistId}")
  public ResponseEntity<ArtistResponse> updateArtist(
      @RequestBody ArtistRequest artistRequest, @PathVariable UUID artistId) {
    ArtistResponse artistResponse = artistService.updateArtist(artistId, artistRequest);
    return ResponseEntity.ok(artistResponse);
  }
}
