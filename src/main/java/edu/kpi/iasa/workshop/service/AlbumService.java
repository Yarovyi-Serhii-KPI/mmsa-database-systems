package edu.kpi.iasa.workshop.service;

import edu.kpi.iasa.workshop.exception.AlbumNotFoundException;
import edu.kpi.iasa.workshop.model.Album;
import edu.kpi.iasa.workshop.repository.AlbumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Album saveAlbum(Album newAlbum) {
        return albumRepository.save(newAlbum);
    }

    public Album getAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            log.info("album: {}", album.get());
            return album.get();
        }
        throw new AlbumNotFoundException();
    }

    public Album updateAlbumById(Long id, Album updatedAlbum) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            Album oldAlbum = album.get();
            log.info("album: {}", oldAlbum);
            updateAlbum(oldAlbum, updatedAlbum);
            return albumRepository.save(oldAlbum);
        }
        throw new AlbumNotFoundException();
    }

    private void updateAlbum(Album oldAlbum, Album updatedAlbum) {
        oldAlbum.setTitle(updatedAlbum.getTitle());
        oldAlbum.setGenre(updatedAlbum.getGenre());
        oldAlbum.setPubYear(updatedAlbum.getPubYear());
        oldAlbum.setArtist(updatedAlbum.getArtist());
    }

    public String deleteAlbumById(Long id) {
        albumRepository.deleteById(id);
        return "Album was successfully deleted!";
    }
}
