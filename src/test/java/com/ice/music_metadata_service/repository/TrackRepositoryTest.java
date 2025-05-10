package com.ice.music_metadata_service.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.ice.music_metadata_service.entity.Artist;
import com.ice.music_metadata_service.entity.Track;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TrackRepositoryTest {

  @Autowired private TrackRepository trackRepository;

  @Autowired private ArtistRepository artistRepository;

  private Artist createArtist(String name) {
    Artist artist = new Artist();
    artist.setName(name);
    return artistRepository.save(artist);
  }

  @Test
  void testSaveTrack() {
    Artist artist = createArtist("Daft Punk");

    Track track = new Track();
    track.setTitle("One More Time");
    track.setGenre("Electronic");
    track.setLength(320);
    track.setArtist(artist);

    Track saved = trackRepository.save(track);

    assertNotNull(saved.getId());
    assertEquals("One More Time", saved.getTitle());
    assertEquals("Electronic", saved.getGenre());
    assertEquals(320, saved.getLength());
    assertEquals(artist.getId(), saved.getArtist().getId());
  }

  @Test
  void testFindById() {
    Artist artist = createArtist("Muse");

    Track track = new Track();
    track.setTitle("Uprising");
    track.setGenre("Rock");
    track.setLength(300);
    track.setArtist(artist);

    Track saved = trackRepository.save(track);

    Optional<Track> found = trackRepository.findById(saved.getId());

    assertTrue(found.isPresent());
    assertEquals("Uprising", found.get().getTitle());
  }

  @Test
  void testFindAll() {
    Artist artist = createArtist("Linkin Park");

    Track track1 = new Track();
    track1.setTitle("Numb");
    track1.setGenre("Rock");
    track1.setLength(210);
    track1.setArtist(artist);

    Track track2 = new Track();
    track2.setTitle("In the End");
    track2.setGenre("Rock");
    track2.setLength(250);
    track2.setArtist(artist);

    trackRepository.saveAll(List.of(track1, track2));

    List<Track> all = trackRepository.findAll();

    assertEquals(2, all.size());
  }

  @Test
  void testDeleteTrack() {
    Artist artist = createArtist("Eminem");

    Track track = new Track();
    track.setTitle("Lose Yourself");
    track.setGenre("Hip-Hop");
    track.setLength(320);
    track.setArtist(artist);

    Track saved = trackRepository.save(track);
    trackRepository.delete(saved);

    Optional<Track> result = trackRepository.findById(saved.getId());
    assertFalse(result.isPresent());
  }

  @Test
  void testFindByArtist() {
    Artist artist1 = createArtist("Coldplay");
    Artist artist2 = createArtist("Adele");

    Track track1 = new Track();
    track1.setTitle("Fix You");
    track1.setGenre("Alternative");
    track1.setLength(270);
    track1.setArtist(artist1);

    Track track2 = new Track();
    track2.setTitle("Hello");
    track2.setGenre("Pop");
    track2.setLength(300);
    track2.setArtist(artist2);

    trackRepository.saveAll(List.of(track1, track2));

    List<Track> coldplayTracks = trackRepository.findByArtist(artist1);

    assertEquals(1, coldplayTracks.size());
    assertEquals("Fix You", coldplayTracks.get(0).getTitle());
  }
}
