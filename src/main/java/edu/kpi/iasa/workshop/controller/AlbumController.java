package edu.kpi.iasa.workshop.controller;

import edu.kpi.iasa.workshop.model.Album;
import edu.kpi.iasa.workshop.model.Artist;
import edu.kpi.iasa.workshop.service.AlbumService;
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
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/album")
    public ResponseEntity<List<Album>> getAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }

    @PostMapping(value = "/album")
    public ResponseEntity<Album> postAlbums(@Valid @RequestBody Album newAlbum) {
        return ResponseEntity.ok(albumService.saveAlbum(newAlbum));
    }

    @GetMapping(value = "/album/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @PutMapping(value = "/album/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @Valid @RequestBody Album updatedAlbum) {
        return ResponseEntity.ok(albumService.updateAlbumById(id, updatedAlbum));
    }

    @DeleteMapping(value = "/album/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.deleteAlbumById(id));
    }
}
