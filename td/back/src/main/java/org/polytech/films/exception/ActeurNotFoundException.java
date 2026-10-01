package org.polytech.films.exception;

public class ActeurNotFoundException extends RuntimeException {

    public ActeurNotFoundException(Long id) {
        super("Acteur non trouvé : " + id);
    }
}
