package edu.kpi.iasa.workshop.controller;

import edu.kpi.iasa.workshop.model.Playlist;
import edu.kpi.iasa.workshop.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import javax.validation.Valid;

@Controller
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping(value = "/playlist")
    public ResponseEntity<List<Playlist>> getPlaylists() {
        return ResponseEntity.ok(playlistService.getPlaylists());
    }

    @PostMapping(value = "/playlist")
    public ResponseEntity<Playlist> postPlaylists(@Valid @RequestBody Playlist newPlaylist) {
        return ResponseEntity.ok(playlistService.savePlaylist(newPlaylist));
    }

    @GetMapping(value = "/playlist/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    @PutMapping(value = "/playlist/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @Valid @RequestBody Playlist updatedPlaylist) {
        return ResponseEntity.ok(playlistService.updatePlaylistById(id, updatedPlaylist));
    }

    @DeleteMapping(value = "/playlist/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.deletePlaylistById(id));
    }
}
