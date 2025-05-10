package com.ice.music_metadata_service.service;

import com.ice.music_metadata_service.dto.TrackRequest;
import com.ice.music_metadata_service.dto.TrackResponse;
import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.entity.Track;
import com.ice.music_metadata_service.exception.NotFoundException;
import com.ice.music_metadata_service.repository.ArtistRepository;
import com.ice.music_metadata_service.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TrackService {
    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;

    public TrackService(TrackRepository trackRepository, ArtistRepository artistRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    public TrackResponse createTrack(TrackRequest request) {
        Artist artist =
            artistRepository
                .findById(request.getArtistId())
                .orElseThrow(() -> new NotFoundException("Artist not found."));

        Track track = new Track();
        track.setArtist(artist);
        track.setTitle(request.getTitle());
        track.setGenre(request.getGenre());
        track.setLength(request.getLength());

        Track saved = trackRepository.save(track);
        return mapToResponse(saved);
    }

    public List<TrackResponse> getTrackByArtist(UUID artistId) {
        Artist artist =
            artistRepository
                .findById(artistId)
                .orElseThrow(() -> new NotFoundException("Artist not found"));

        return trackRepository.findByArtist(artist).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private TrackResponse mapToResponse(Track track) {
        UUID artistId = track.getArtist() != null ? track.getArtist().getId() : null;
        return new TrackResponse(
            track.getId(),
            track.getTitle(),
            track.getGenre(),
            track.getLength(),
            track.getArtist().getId(),
            track.getCreatedAt(),
            track.getUpdatedAt());
    }
}
