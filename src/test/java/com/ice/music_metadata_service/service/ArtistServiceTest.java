package com.ice.music_metadata_service.service;

import com.ice.music_metadata_service.dto.ArtistRequest;
import com.ice.music_metadata_service.dto.ArtistResponse;
import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateArtist() {
        ArtistRequest request = new ArtistRequest("Test Artist");
        Artist savedArtist = new Artist();
        savedArtist.setId(UUID.randomUUID());
        savedArtist.setName("Test Artist");
        savedArtist.setCreatedAt(LocalDateTime.now());

        when(artistRepository.save(any(Artist.class))).thenReturn(savedArtist);

        ArtistResponse response = artistService.createArtist(request);

        assertEquals("Test Artist", response.getName());
        assertNotNull(response.getId());
        verify(artistRepository, times(1)).save(any(Artist.class));
    }

    @Test
    void testUpdateArtist() {
        UUID artistId = UUID.randomUUID();
        Artist existing = new Artist();
        existing.setId(artistId);
        existing.setName("Old Name");

        when(artistRepository.findById(artistId)).thenReturn(Optional.of(existing));
        when(artistRepository.save(any(Artist.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        ArtistRequest updateRequest = new ArtistRequest("New Name");
        ArtistResponse response = artistService.updateArtist(artistId, updateRequest);

        assertEquals("New Name", response.getName());
        verify(artistRepository).findById(artistId);
        verify(artistRepository).save(any(Artist.class));
    }
}
