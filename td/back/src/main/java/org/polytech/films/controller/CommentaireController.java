package org.polytech.films.controller;

import java.net.URI;
import java.util.List;

import org.polytech.films.model.Commentaire;
import org.polytech.films.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CommentaireController {

    private final FilmService filmService;

    public CommentaireController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films/{id}/commentaires")
    public List<Commentaire> getCommentaires(@PathVariable Long id) {
        return filmService.getCommentaires(id);
    }

    @PostMapping("/films/{id}/commentaires")
    public ResponseEntity<Commentaire> ajouterCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        Commentaire saved = filmService.ajouterCommentaire(id, commentaire);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/commentaires/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/commentaires/{id}")
    public Commentaire mettreAJourCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        return filmService.mettreAJourCommentaire(id, commentaire);
    }

    @DeleteMapping("/commentaires/{id}")
    public ResponseEntity<Void> supprimerCommentaire(@PathVariable Long id) {
        filmService.supprimerCommentaire(id);
        return ResponseEntity.noContent().build();
    }
}
