package com.ice.music_metadata_service.service;

import com.ice.music_metadata_service.dto.ArtistRequest;
import com.ice.music_metadata_service.dto.ArtistResponse;
import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.exception.NotFoundException;
import com.ice.music_metadata_service.repository.ArtistRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Service layer for managing operations related to artists. Handles creation, update, and ordered
 * retrieval of artist data.
 */
@Service
public class ArtistService {
  private final ArtistRepository artistRepository;

  public ArtistService(ArtistRepository artistRepository) {
    this.artistRepository = artistRepository;
  }

  /**
   * Creates a new artist from the given request data.
   *
   * @param request the request containing the artist's name
   * @return the created artist as a response DTO
   */
  public ArtistResponse createArtist(ArtistRequest request) {
    Artist artist = new Artist();
    artist.setName(request.getName());

    Artist saved = artistRepository.save(artist);
    return mapToResponse(saved);
  }

  /**
   * Updates an existing artist's name using the given ID and request data.
   *
   * @param id the UUID of the artist to update
   * @param request the request containing the new name
   * @return the updated artist as a response DTO
   */
  public ArtistResponse updateArtist(UUID id, ArtistRequest request) {
    Artist artist =
        artistRepository.findById(id).orElseThrow(() -> new NotFoundException("Artist not found"));

    artist.setName(request.getName());
    Artist updated = artistRepository.save(artist);
    return mapToResponse(updated);
  }

  /**
   * Retrieves all artists sorted by their creation date in ascending order.
   *
   * @return a list of all artists ordered by creation time
   */
  public List<Artist> getAllArtistOrdered() {
    return artistRepository.findAllByOrderByCreatedAtAsc();
  }

  /**
   * Converts an Artist entity into an ArtistResponse DTO.
   *
   * @param artist the Artist entity to convert
   * @return the corresponding ArtistResponse
   */
  private ArtistResponse mapToResponse(Artist artist) {
    return new ArtistResponse(
        artist.getId(), artist.getName(), artist.getCreatedAt(), artist.getUpdatedAt());
  }
}
