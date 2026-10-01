package org.polytech.films.service;

import java.util.List;

import org.polytech.films.dto.ActeurCreationDto;
import org.polytech.films.dto.ActeurDto;
import org.polytech.films.dto.ActeurMapper;
import org.polytech.films.dto.FilmDto;
import org.polytech.films.dto.FilmMapper;
import org.polytech.films.exception.ActeurNotFoundException;
import org.polytech.films.model.Acteur;
import org.polytech.films.repository.ActeurRepository;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActeurService {

    private final ActeurRepository acteurRepository;
    private final FilmRepository filmRepository;

    public ActeurService(ActeurRepository acteurRepository, FilmRepository filmRepository) {
        this.acteurRepository = acteurRepository;
        this.filmRepository = filmRepository;
    }

    public List<ActeurDto> listerActeurs() {
        return acteurRepository.findAll().stream()
            .map(ActeurMapper::toDto)
            .toList();
    }

    public ActeurDto RetourneActeurParId(Long id) {
        return ActeurMapper.toDto(trouverActeurParId(id));
    }

    private Acteur trouverActeurParId(Long id) {
        return acteurRepository.findById(id)
            .orElseThrow(() -> new ActeurNotFoundException(id));
    }

    public ActeurDto ajouterUnActeur(ActeurCreationDto acteurCreationDto) {
        Acteur acteur = ActeurMapper.toEntity(acteurCreationDto);
        return ActeurMapper.toDto(acteurRepository.save(acteur));
    }

    public ActeurDto mettreAJourUnActeur(Long id, ActeurCreationDto acteurCreationDto) {
        trouverActeurParId(id);
        Acteur acteur = ActeurMapper.toEntity(acteurCreationDto);
        acteur.setId(id);
        return ActeurMapper.toDto(acteurRepository.save(acteur));
    }

    public void supprimerUnActeur(Long id) {
        trouverActeurParId(id);
        acteurRepository.deleteById(id);
    }

    public List<FilmDto> getFilmsDeLActeur(Long acteurId) {
        trouverActeurParId(acteurId);
        return filmRepository.findByActeursId(acteurId).stream()
            .map(FilmMapper::toDto)
            .toList();
    }
}
