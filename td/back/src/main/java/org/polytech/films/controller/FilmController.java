package org.polytech.films.controller;

import java.util.Map;

import org.polytech.films.model.Film;
import org.polytech.films.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class FilmController {

    private final FilmService filmService;

    //Construsteur + injection par constructeur
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/films")
    public Map<Long,Film> ListeFilms(){
        return filmService.RetournerListeFilm();
    }

    @GetMapping("/films/{id}")
    public Film RetourneFilmParId(@PathVariable Long id){
        return filmService.RetourneFilmParId(id);

    }






}
