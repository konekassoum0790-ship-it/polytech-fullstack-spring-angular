package org.polytech.films.model;

import java.time.LocalDate;

public class Commentaire {

    private Long id;
    private String auteur;
    private LocalDate date;
    private String message;

    public Commentaire() {
    }

    public Commentaire(Long id, String auteur, LocalDate date, String message) {
        this.id = id;
        this.auteur = auteur;
        this.date = date;
        this.message = message;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
