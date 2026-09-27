package org.polytech.films.exception;

public class CommentaireNotFoundException extends RuntimeException {

    public CommentaireNotFoundException(Long id) {
        super("Commentaire non trouvé : " + id);
    }
}
