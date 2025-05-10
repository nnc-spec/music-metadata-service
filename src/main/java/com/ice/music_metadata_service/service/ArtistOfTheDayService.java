package com.ice.music_metadata_service.service;

import com.ice.music_metadata_service.dto.ArtistResponse;
import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ArtistOfTheDayService {
    private final ArtistService artistService;

    public ArtistOfTheDayService(ArtistService artistService) {
        this.artistService = artistService;
    }

    public ArtistResponse getArtistOfTheDay() {
        List<Artist> artists = artistService.getAllArtistOrdered();

        if (artists.isEmpty()) {
            throw new NotFoundException("No artists found");
        }

        long daysSinceEpoch =
            ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), LocalDate.now(ZoneOffset.UTC));

        int index = (int) (daysSinceEpoch % artists.size());
        Artist selected = artists.get(index);

        return new ArtistResponse(
            selected.getId(), selected.getName(), selected.getCreatedAt(), selected.getUpdatedAt());
    }
}
