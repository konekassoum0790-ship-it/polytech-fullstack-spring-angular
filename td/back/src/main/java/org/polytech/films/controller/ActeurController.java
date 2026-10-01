package org.polytech.films.controller;

import java.net.URI;
import java.util.List;

import org.polytech.films.dto.ActeurCreationDto;
import org.polytech.films.dto.ActeurDto;
import org.polytech.films.dto.FilmDto;
import org.polytech.films.service.ActeurService;
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
public class ActeurController {

    private final ActeurService acteurService;

    public ActeurController(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    @GetMapping("/acteurs")
    public List<ActeurDto> listerActeurs() {
        return acteurService.listerActeurs();
    }

    @GetMapping("/acteurs/{id}")
    public ActeurDto RetourneActeurParId(@PathVariable Long id) {
        return acteurService.RetourneActeurParId(id);
    }

    @PostMapping("/acteurs")
    public ResponseEntity<ActeurDto> ajouterUnActeur(@RequestBody ActeurCreationDto acteurCreationDto) {
        ActeurDto saved = acteurService.ajouterUnActeur(acteurCreationDto);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/acteurs/{id}")
    public ActeurDto mettreAJourUnActeur(@PathVariable Long id, @RequestBody ActeurCreationDto acteurCreationDto) {
        return acteurService.mettreAJourUnActeur(id, acteurCreationDto);
    }

    @DeleteMapping("/acteurs/{id}")
    public ResponseEntity<Void> supprimerUnActeur(@PathVariable Long id) {
        acteurService.supprimerUnActeur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/acteurs/{id}/films")
    public List<FilmDto> getFilmsDeLActeur(@PathVariable Long id) {
        return acteurService.getFilmsDeLActeur(id);
    }
}
