package org.polytech.films.repository;

import java.util.List;

import org.polytech.films.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {

    @Query("select a from Acteur a join a.films f where f.id = :filmId")
    List<Acteur> findByFilmsId(@Param("filmId") Long filmId);
}
