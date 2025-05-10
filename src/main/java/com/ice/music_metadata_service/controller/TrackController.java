package com.ice.music_metadata_service.controller;

import com.ice.music_metadata_service.dto.TrackRequest;
import com.ice.music_metadata_service.dto.TrackResponse;
import com.ice.music_metadata_service.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<TrackResponse> createTrack(@RequestBody TrackRequest trackRequest) {
        TrackResponse trackResponse = trackService.createTrack(trackRequest);
        return new ResponseEntity<>(trackResponse, HttpStatus.CREATED);
    }

    @GetMapping("/by-artist/{artistId}")
    public ResponseEntity<List<TrackResponse>> getTrackByArtist(@PathVariable UUID artistId) {
        List<TrackResponse> trackList = trackService.getTrackByArtist(artistId);
        return ResponseEntity.ok(trackList);
    }
}
