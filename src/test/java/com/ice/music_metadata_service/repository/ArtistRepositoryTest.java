package com.ice.music_metadata_service.repository;

import com.ice.music_metadata_service.entity.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testSave() {
        Artist artist = new Artist();
        artist.setName("Imagine Dragons");

        Artist savedArtist = artistRepository.save(artist);

        assertNotNull(savedArtist.getId());
        assertNotNull(savedArtist.getCreatedAt());
        assertEquals("Imagine Dragons", savedArtist.getName());
    }

    @Test
    void testFindById() {
        Artist artist = new Artist();
        artist.setName("Muse");
        Artist saved = artistRepository.save(artist);

        Optional<Artist> found = artistRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Muse", found.get().getName());
    }

    @Test
    void testFindAll() {
        Artist a1 = new Artist();
        a1.setName("Adele");
        Artist a2 = new Artist();
        a2.setName("Beyonce");

        artistRepository.saveAll(List.of(a1, a2));

        List<Artist> artists = artistRepository.findAll();

        assertEquals(2, artists.size());
    }

    @Test
    void testDelete() {
        Artist artist = new Artist();
        artist.setName("Eminem");
        Artist saved = artistRepository.save(artist);

        artistRepository.delete(saved);

        Optional<Artist> found = artistRepository.findById(saved.getId());
        assertFalse(found.isPresent());
    }

    @Test
    void testFindByNameContainingIgnoreCase() {
        Artist artist = new Artist();
        artist.setName("Linkin Park");
        artistRepository.save(artist);

        List<Artist> result = artistRepository.findByNameContainingIgnoreCase("linkin");

        assertEquals(1, result.size());
        assertEquals("Linkin Park", result.get(0).getName());
    }

    @Test
    void testFindAllByOrderByCreatedAtAsc() {
        Artist a1 = new Artist();
        a1.setName("First");
        Artist a2 = new Artist();
        a2.setName("Second");

        artistRepository.save(a1);
        artistRepository.save(a2);

        List<Artist> result = artistRepository.findAllByOrderByCreatedAtAsc();

        assertEquals(2, result.size());
        assertTrue(
            result.get(0).getCreatedAt().isBefore(result.get(1).getCreatedAt())
                || result.get(0).getCreatedAt().isEqual(result.get(1).getCreatedAt()));
    }
}
