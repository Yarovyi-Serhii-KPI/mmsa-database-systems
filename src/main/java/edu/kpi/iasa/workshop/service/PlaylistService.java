package edu.kpi.iasa.workshop.service;

import edu.kpi.iasa.workshop.exception.PlaylistNotFoundException;
import edu.kpi.iasa.workshop.model.Playlist;
import edu.kpi.iasa.workshop.repository.PlaylistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist savePlaylist(Playlist newPlaylist) {
        return playlistRepository.save(newPlaylist);
    }

    public Playlist getPlaylistById(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent()) {
            log.info("playlist: {}", playlist.get());
            return playlist.get();
        }
        throw new PlaylistNotFoundException();
    }

    public Playlist updatePlaylistById(Long id, Playlist updatedPlaylist) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent()) {
            Playlist oldPlaylist = playlist.get();
            log.info("playlist: {}", oldPlaylist);
            updatePlaylist(oldPlaylist, updatedPlaylist);
            return playlistRepository.save(oldPlaylist);
        }
        throw new PlaylistNotFoundException();
    }

    private void updatePlaylist(Playlist oldPlaylist, Playlist updatedPlaylist) {
        oldPlaylist.setName(updatedPlaylist.getName());
        oldPlaylist.setLength(updatedPlaylist.getLength());
        oldPlaylist.setSize(updatedPlaylist.getSize());
        oldPlaylist.setSong_number(updatedPlaylist.getSong_number());
    }

    public String deletePlaylistById(Long id) {
        playlistRepository.deleteById(id);
        return "Playlist was successfully deleted!";
    }
}
