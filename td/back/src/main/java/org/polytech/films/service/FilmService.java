package org.polytech.films.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.polytech.films.exception.CommentaireNotFoundException;
import org.polytech.films.exception.FilmNotFoundException;
import org.polytech.films.model.Commentaire;
import org.polytech.films.model.Film;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmService {


    private final FilmRepository filmRepository;
    private final Map<Long, List<Commentaire>> commentairesParFilm = new HashMap<>();
    private Long prochainCommentaireId = 1L;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    public List<Film> RetournerListeFilm(String realisateur, Film.Genre genre){
        return filmRepository.rechercherFilms(realisateur, genre).stream()
            .map(this::attacherCommentaires)
            .toList();
    }

    public Film RetourneFilmParId(Long id){
        Film film = filmRepository.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));
        return attacherCommentaires(film);
    }


    public Film ajouterUnFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film mettreAJourUnFilm(Long id, Film film) {
        RetourneFilmParId(id);
        film.setId(id);
        return filmRepository.save(film);
    }

    public void supprimerUnFilm(Long id) {
        RetourneFilmParId(id);
        filmRepository.deleteById(id);
    }

    private Film attacherCommentaires(Film film) {
        film.setCommentaires(commentairesParFilm.getOrDefault(film.getId(), new ArrayList<>()));
        return film;
    }

    public List<Commentaire> getCommentaires(Long filmId) {
        return RetourneFilmParId(filmId).getCommentaires();
    }

    public Commentaire ajouterCommentaire(Long filmId, Commentaire commentaire) {
        RetourneFilmParId(filmId);
        commentaire.setId(prochainCommentaireId++);
        commentairesParFilm.computeIfAbsent(filmId, id -> new ArrayList<>()).add(commentaire);
        return commentaire;
    }

    public Commentaire mettreAJourCommentaire(Long commentaireId, Commentaire commentaire) {
        for (List<Commentaire> commentaires : commentairesParFilm.values()) {
            for (int i = 0; i < commentaires.size(); i++) {
                if (commentaires.get(i).getId().equals(commentaireId)) {
                    commentaire.setId(commentaireId);
                    commentaires.set(i, commentaire);
                    return commentaire;
                }
            }
        }
        throw new CommentaireNotFoundException(commentaireId);
    }

    public void supprimerCommentaire(Long commentaireId) {
        for (List<Commentaire> commentaires : commentairesParFilm.values()) {
            if (commentaires.removeIf(c -> c.getId().equals(commentaireId))) {
                return;
            }
        }
        throw new CommentaireNotFoundException(commentaireId);
    }
}
