package org.polytech.films.controller;

import java.net.URI;
import java.util.List;

import org.polytech.films.dto.ActeurDto;
import org.polytech.films.dto.FilmCreationDto;
import org.polytech.films.dto.FilmDetailDto;
import org.polytech.films.dto.FilmDto;
import org.polytech.films.model.Genre;
import org.polytech.films.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FilmController {

    private final FilmService filmService;

    //Construsteur + injection par constructeur
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/films")
    public List<FilmDto> ListeFilms(
        @RequestParam(required = false) String realisateur,
        @RequestParam(required = false) Genre genre){
        return filmService.RetournerListeFilm(realisateur, genre);
    }

    @GetMapping("/films/{id}")
    public FilmDetailDto RetourneFilmParId(@PathVariable Long id){
        return filmService.RetourneFilmParId(id);

    }

    @GetMapping("/films/{id}/acteurs")
    public List<ActeurDto> getActeursDuFilm(@PathVariable Long id){
        return filmService.getActeursDuFilm(id);
    }

    @PostMapping("/films/{id}/acteurs/{acteurId}")
    public FilmDetailDto ajouterActeurAuFilm(@PathVariable Long id, @PathVariable Long acteurId){
        return filmService.ajouterActeurAuFilm(id, acteurId);
    }

    @DeleteMapping("/films/{id}/acteurs/{acteurId}")
    public ResponseEntity<Void> retirerActeurDuFilm(@PathVariable Long id, @PathVariable Long acteurId){
        filmService.retirerActeurDuFilm(id, acteurId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/films")
    public ResponseEntity<FilmDto> ajouterUnFilm(@Valid @RequestBody FilmCreationDto filmCreationDto){
        FilmDto saved = filmService.ajouterUnFilm(filmCreationDto);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(uri).body(saved);

    }

    @PutMapping("/films/{id}")
    public FilmDto mettreAJourUnFilm(@PathVariable Long id, @RequestBody FilmCreationDto filmCreationDto){
        return filmService.mettreAJourUnFilm(id, filmCreationDto);
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> supprimerUnFilm(@PathVariable Long id){
        filmService.supprimerUnFilm(id);
        return ResponseEntity.noContent().build();
    }




}
