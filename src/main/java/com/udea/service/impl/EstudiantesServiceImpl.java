package com.udea.service.impl;

import com.udea.domain.Estudiantes;
import com.udea.repository.EstudiantesRepository;
import com.udea.service.EstudiantesService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Estudiantes}.
 */
@Service
@Transactional
public class EstudiantesServiceImpl implements EstudiantesService {

    private final Logger log = LoggerFactory.getLogger(EstudiantesServiceImpl.class);

    private final EstudiantesRepository estudiantesRepository;

    public EstudiantesServiceImpl(EstudiantesRepository estudiantesRepository) {
        this.estudiantesRepository = estudiantesRepository;
    }

    @Override
    public Estudiantes save(Estudiantes estudiantes) {
        log.debug("Request to save Estudiantes : {}", estudiantes);
        return estudiantesRepository.save(estudiantes);
    }

    @Override
    public Estudiantes update(Estudiantes estudiantes) {
        log.debug("Request to update Estudiantes : {}", estudiantes);
        return estudiantesRepository.save(estudiantes);
    }

    @Override
    public Optional<Estudiantes> partialUpdate(Estudiantes estudiantes) {
        log.debug("Request to partially update Estudiantes : {}", estudiantes);

        return estudiantesRepository
            .findById(estudiantes.getId())
            .map(existingEstudiantes -> {
                if (estudiantes.getNombreestudiante() != null) {
                    existingEstudiantes.setNombreestudiante(estudiantes.getNombreestudiante());
                }
                if (estudiantes.getEmail() != null) {
                    existingEstudiantes.setEmail(estudiantes.getEmail());
                }
                if (estudiantes.getTelefono() != null) {
                    existingEstudiantes.setTelefono(estudiantes.getTelefono());
                }

                return existingEstudiantes;
            })
            .map(estudiantesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Estudiantes> findAll(Pageable pageable) {
        log.debug("Request to get all Estudiantes");
        return estudiantesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estudiantes> findOne(Long id) {
        log.debug("Request to get Estudiantes : {}", id);
        return estudiantesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Estudiantes : {}", id);
        estudiantesRepository.deleteById(id);
    }
}
