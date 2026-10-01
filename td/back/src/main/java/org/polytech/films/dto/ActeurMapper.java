package org.polytech.films.dto;

import org.polytech.films.model.Acteur;

public class ActeurMapper {

    public static ActeurDto toDto(Acteur acteur) {
        return new ActeurDto(acteur.getId(), acteur.getNom(), acteur.getPrenom());
    }

    public static Acteur toEntity(ActeurCreationDto dto) {
        Acteur acteur = new Acteur();
        acteur.setNom(dto.nom());
        acteur.setPrenom(dto.prenom());
        return acteur;
    }
}
