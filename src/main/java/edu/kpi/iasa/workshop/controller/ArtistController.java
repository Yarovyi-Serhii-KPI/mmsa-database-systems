package edu.kpi.iasa.workshop.controller;

import edu.kpi.iasa.workshop.model.Artist;
import edu.kpi.iasa.workshop.service.ArtistService;
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
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = "/artist")
    public ResponseEntity<List<Artist>> getArtists() {
        return ResponseEntity.ok(artistService.getArtists());
    }

    @PostMapping(value = "/artist")
    public ResponseEntity<Artist> postArtists(@Valid @RequestBody Artist newArtist) {
        return ResponseEntity.ok(artistService.saveArtist(newArtist));
    }

    @GetMapping(value = "/artist/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PutMapping(value = "/artist/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @Valid @RequestBody Artist updatedArtist) {
        return ResponseEntity.ok(artistService.updateArtistById(id, updatedArtist));
    }

    @DeleteMapping(value = "/artist/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.deleteArtistById(id));
    }
}
