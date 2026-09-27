package org.polytech.films.service;

import java.util.List;

import org.polytech.films.exception.CommentaireNotFoundException;
import org.polytech.films.exception.FilmNotFoundException;
import org.polytech.films.model.Commentaire;
import org.polytech.films.model.Film;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;

@Service 
public class FilmService {


    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    

    public List<Film> RetournerListeFilm(String realisateur, Film.Genre genre){
        return filmRepository.rechercherFilms(realisateur, genre);
    }

    public Film RetourneFilmParId(Long id){
        Film film = filmRepository.RetourneFilmParId(id);
        if (film == null) {
            throw new FilmNotFoundException(id);
        }
        return film;
    }


    public Film ajouterUnFilm(Film film) {
        return filmRepository.ajouterUnFilm(film);
    }

    public Film mettreAJourUnFilm(Long id, Film film) {
        RetourneFilmParId(id);
        return filmRepository.mettreAJourUnFilm(id, film);
    }

    public void supprimerUnFilm(Long id) {
        RetourneFilmParId(id);
        filmRepository.supprimerUnFilm(id);
    }

    public List<Commentaire> getCommentaires(Long filmId) {
        RetourneFilmParId(filmId);
        return filmRepository.getCommentaires(filmId);
    }

    public Commentaire ajouterCommentaire(Long filmId, Commentaire commentaire) {
        RetourneFilmParId(filmId);
        return filmRepository.ajouterCommentaire(filmId, commentaire);
    }

    public Commentaire mettreAJourCommentaire(Long commentaireId, Commentaire commentaire) {
        Commentaire result = filmRepository.mettreAJourCommentaire(commentaireId, commentaire);
        if (result == null) throw new CommentaireNotFoundException(commentaireId);
        return result;
    }

    public void supprimerCommentaire(Long commentaireId) {
        boolean removed = filmRepository.supprimerCommentaire(commentaireId);
        if (!removed) throw new CommentaireNotFoundException(commentaireId);
    }
}
