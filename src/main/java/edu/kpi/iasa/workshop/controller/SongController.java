package edu.kpi.iasa.workshop.controller;

import edu.kpi.iasa.workshop.model.Song;
import edu.kpi.iasa.workshop.service.SongService;
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
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/song")
    public ResponseEntity<List<Song>> getSongs() {
        return ResponseEntity.ok(songService.getSongs());
    }

    @PostMapping(value = "/song")
    public ResponseEntity<Song> postSong(@Valid @RequestBody Song newSong) {
        return ResponseEntity.ok(songService.saveSong(newSong));
    }

    @GetMapping(value = "/song/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @PutMapping(value = "/song/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @Valid @RequestBody Song updatedSong) {
        return ResponseEntity.ok(songService.updateSongById(id, updatedSong));
    }

    @DeleteMapping(value = "/song/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        return ResponseEntity.ok(songService.deleteSongById(id));
    }
}
