package com.ice.music_metadata_service.repository;

import com.ice.music_metadata_service.entity.Artist;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
  List<Artist> findAllByOrderByCreatedAtAsc();

  List<Artist> findByNameContainingIgnoreCase(String name);
}
