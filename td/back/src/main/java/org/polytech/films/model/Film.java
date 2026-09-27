package org.polytech.films.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class Film {

    private Long id;
    @NotBlank
    private String titre;
    private String realisateur;
    private LocalDate dateSortie;
    public enum Genre {ACTION, DRAME, COMEDIE, SCIENCE_FICTION}
    public Genre genre;
    private List<Commentaire> commentaires = new ArrayList<>();

    public Film() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getRealisateur() {
        return realisateur;
    }
    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }
    public LocalDate getDateSortie() {
        return dateSortie;
    }
    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public List<Commentaire> getCommentaires() {
        return commentaires;
    }
    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    //Constructeur avec initialisation
    public Film(Long id, String titre, String realisateur, LocalDate dateSortie, Genre genre) {
        this.id = id;
        this.titre = titre;
        this.realisateur = realisateur;
        this.dateSortie = dateSortie;
        this.genre = genre;
    }

    
}
