package org.polytech.films.repository;

import java.util.List;

import org.polytech.films.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {

    List<Acteur> findByFilmsId(Long filmId);
}
