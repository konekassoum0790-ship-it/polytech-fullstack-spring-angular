package org.polytech.films.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Film {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable=false, length=200)
    private String titre;
    private String realisateur;
    private LocalDate dateSortie;
    public Genre genre;
    @Transient 
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
}
