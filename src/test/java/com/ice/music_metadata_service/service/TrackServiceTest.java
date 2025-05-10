package com.ice.music_metadata_service.service;

import com.ice.music_metadata_service.dto.TrackRequest;
import com.ice.music_metadata_service.dto.TrackResponse;
import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.entity.Track;
import com.ice.music_metadata_service.exception.NotFoundException;
import com.ice.music_metadata_service.repository.ArtistRepository;
import com.ice.music_metadata_service.repository.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    private TrackRepository trackRepository;
    private ArtistRepository artistRepository;
    private TrackService trackService;

    @BeforeEach
    void setUp() {
        trackRepository = mock(TrackRepository.class);
        artistRepository = mock(ArtistRepository.class);
        trackService = new TrackService(trackRepository, artistRepository);
    }

    @Test
    void testCreateTrack_whenArtistExists_shouldSaveTrackAndReturnResponse() {
        // Arrange
        UUID artistId = UUID.randomUUID();
        TrackRequest request = new TrackRequest(artistId, "Track Title", "Jazz", 180);

        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setName("Test Artist");

        Track savedTrack = new Track();
        savedTrack.setId(UUID.randomUUID());
        savedTrack.setTitle("Track Title");
        savedTrack.setGenre("Jazz");
        savedTrack.setLength(180);
        savedTrack.setArtist(artist);

        when(artistRepository.findById(artistId)).thenReturn(Optional.of(artist));
        when(trackRepository.save(any(Track.class))).thenReturn(savedTrack);

        // Act
        TrackResponse result = trackService.createTrack(request);

        // Assert
        assertNotNull(result);
        assertEquals("Track Title", result.getTitle());
        assertEquals("Jazz", result.getGenre());
        assertEquals(180, result.getLength());
        assertEquals(artistId, result.getArtistId());
    }

    @Test
    void testCreateTrack_whenArtistDoesNotExist_shouldThrowNotFoundException() {
        // Arrange
        UUID artistId = UUID.randomUUID();
        TrackRequest request = new TrackRequest(artistId, "Track Title", "Jazz", 180);

        when(artistRepository.findById(artistId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> trackService.createTrack(request));
    }
}
