package org.polytech.films.controller;

import java.net.URI;
import java.util.List;

import org.polytech.films.model.Film;
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
    public List<Film> ListeFilms(
        @RequestParam(required = false) String realisateur,
        @RequestParam(required = false) Film.Genre genre){
        return filmService.RetournerListeFilm(realisateur, genre);
    }

    @GetMapping("/films/{id}")
    public Film RetourneFilmParId(@PathVariable Long id){
        return filmService.RetourneFilmParId(id);

    }

    @PostMapping("/films")
    public ResponseEntity<Film>ajouterUnFilm(@Valid @RequestBody Film film){
        Film saved = filmService.ajouterUnFilm(film);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);

    }

    @PutMapping("/films/{id}")
    public Film mettreAJourUnFilm(@PathVariable Long id, @RequestBody Film film){
        return filmService.mettreAJourUnFilm(id, film);
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> supprimerUnFilm(@PathVariable Long id){
        filmService.supprimerUnFilm(id);
        return ResponseEntity.noContent().build();
    }




}
