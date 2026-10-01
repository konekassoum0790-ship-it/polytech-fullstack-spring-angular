package org.polytech.films.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.polytech.films.dto.ActeurDto;
import org.polytech.films.dto.ActeurMapper;
import org.polytech.films.dto.FilmCreationDto;
import org.polytech.films.dto.FilmDetailDto;
import org.polytech.films.dto.FilmDto;
import org.polytech.films.dto.FilmMapper;
import org.polytech.films.exception.ActeurNotFoundException;
import org.polytech.films.exception.CommentaireNotFoundException;
import org.polytech.films.exception.FilmNotFoundException;
import org.polytech.films.model.Acteur;
import org.polytech.films.model.Commentaire;
import org.polytech.films.model.Film;
import org.polytech.films.model.Genre;
import org.polytech.films.repository.ActeurRepository;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FilmService {


    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;
    private final Map<Long, List<Commentaire>> commentairesParFilm = new HashMap<>();
    private Long prochainCommentaireId = 1L;

    public FilmService(FilmRepository filmRepository, ActeurRepository acteurRepository) {
        this.filmRepository = filmRepository;
        this.acteurRepository = acteurRepository;
    }


    public List<FilmDto> RetournerListeFilm(String realisateur, Genre genre){
        return filmRepository.rechercherFilms(realisateur, genre).stream()
            .map(FilmMapper::toDto)
            .toList();
    }

    public FilmDetailDto RetourneFilmParId(Long id){
        return FilmMapper.toDetailDto(trouverFilmParId(id));
    }

    private Film trouverFilmParId(Long id) {
        return filmRepository.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));
    }

    private Acteur trouverActeurParId(Long id) {
        return acteurRepository.findById(id)
            .orElseThrow(() -> new ActeurNotFoundException(id));
    }

    public List<ActeurDto> getActeursDuFilm(Long filmId) {
        return trouverFilmParId(filmId).getActeurs().stream()
            .map(ActeurMapper::toDto)
            .toList();
    }

    public FilmDetailDto ajouterActeurAuFilm(Long filmId, Long acteurId) {
        Film film = trouverFilmParId(filmId);
        Acteur acteur = trouverActeurParId(acteurId);
        film.getActeurs().add(acteur);
        filmRepository.save(film);
        return FilmMapper.toDetailDto(film);
    }

    public void retirerActeurDuFilm(Long filmId, Long acteurId) {
        Film film = trouverFilmParId(filmId);
        Acteur acteur = trouverActeurParId(acteurId);
        film.getActeurs().remove(acteur);
        filmRepository.save(film);
    }


    public FilmDto ajouterUnFilm(FilmCreationDto filmCreationDto) {
        Film film = FilmMapper.toEntity(filmCreationDto);
        return FilmMapper.toDto(filmRepository.save(film));
    }

    public FilmDto mettreAJourUnFilm(Long id, FilmCreationDto filmCreationDto) {
        trouverFilmParId(id);
        Film film = FilmMapper.toEntity(filmCreationDto);
        film.setId(id);
        return FilmMapper.toDto(filmRepository.save(film));
    }

    public void supprimerUnFilm(Long id) {
        trouverFilmParId(id);
        filmRepository.deleteById(id);
    }

    public List<Commentaire> getCommentaires(Long filmId) {
        trouverFilmParId(filmId);
        return commentairesParFilm.getOrDefault(filmId, new ArrayList<>());
    }

    public Commentaire ajouterCommentaire(Long filmId, Commentaire commentaire) {
        trouverFilmParId(filmId);
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
