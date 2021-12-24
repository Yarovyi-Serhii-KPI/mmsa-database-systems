package edu.kpi.iasa.workshop.service;

import edu.kpi.iasa.workshop.exception.SongNotFoundException;
import edu.kpi.iasa.workshop.model.Song;
import edu.kpi.iasa.workshop.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public Song saveSong(Song newSong) {
        return songRepository.save(newSong);
    }

    public Song getSongById(Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            log.info("song: {}", song.get());
            return song.get();
        }
        throw new SongNotFoundException();
    }

    public Song updateSongById(Long id, Song updatedSong) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            Song oldSong = song.get();
            log.info("song: {}", oldSong);
            updateSong(oldSong, updatedSong);
            return songRepository.save(oldSong);
        }
        throw new SongNotFoundException();
    }

    private void updateSong(Song oldSong, Song updatedSong) {
        oldSong.setName(updatedSong.getName());
        oldSong.setAlbum_id(updatedSong.getAlbum_id());
        oldSong.setMilliseconds(updatedSong.getMilliseconds());
        oldSong.setBytes(updatedSong.getBytes());
        oldSong.setUploaded_by(updatedSong.getUploaded_by());
    }

    public String deleteSongById(Long id) {
        songRepository.deleteById(id);
        return "Song was successfully deleted!";
    }
}
