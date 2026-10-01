package org.polytech.films.repository;

import java.util.List;

import org.polytech.films.model.Film;
import org.polytech.films.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("""
        select f from Film f
        where (:realisateur is null or f.realisateur = :realisateur)
          and (:genre is null or f.genre = :genre)
        """)
    List<Film> rechercherFilms(@Param("realisateur") String realisateur, @Param("genre") Genre genre);

    List<Film> findByActeursId(Long acteurId);
}
