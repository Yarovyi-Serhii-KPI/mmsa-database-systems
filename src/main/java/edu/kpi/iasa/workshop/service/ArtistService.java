package edu.kpi.iasa.workshop.service;

import edu.kpi.iasa.workshop.exception.ArtistNotFoundException;
import edu.kpi.iasa.workshop.model.Artist;
import edu.kpi.iasa.workshop.repository.ArtistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    public Artist saveArtist(Artist newArtist) {
        return artistRepository.save(newArtist);
    }

    public Artist getArtistById(Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            log.info("artist: {}", artist.get());
            return artist.get();
        }
        throw new ArtistNotFoundException();
    }

    public Artist updateArtistById(Long id, Artist updatedArtist) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            Artist oldArtist = artist.get();
            log.info("artist: {}", oldArtist);
            updateArtist(oldArtist, updatedArtist);
            return artistRepository.save(oldArtist);
        }
        throw new ArtistNotFoundException();
    }

    private void updateArtist(Artist oldArtist, Artist updatedArtist) {
        oldArtist.setName(updatedArtist.getName());
    }

    public String deleteArtistById(Long id) {
        artistRepository.deleteById(id);
        return "Artist was successfully deleted!";
    }

}
