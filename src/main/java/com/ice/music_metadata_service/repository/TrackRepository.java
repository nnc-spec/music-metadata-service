package com.ice.music_metadata_service.repository;

import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, UUID> {
    List<Track> findByArtist(Artist artist);
}
