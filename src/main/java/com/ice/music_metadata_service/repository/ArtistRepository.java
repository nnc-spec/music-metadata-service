package com.ice.music_metadata_service.repository;

import com.ice.music_metadata_service.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    List<Artist> findAllByOrderByCreatedAtAsc();

    List<Artist> findByNameContainingIgnoreCase(String name);
}
