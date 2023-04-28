package com.udea.service;

import com.udea.domain.Estudiantes;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Estudiantes}.
 */
public interface EstudiantesService {
    /**
     * Save a estudiantes.
     *
     * @param estudiantes the entity to save.
     * @return the persisted entity.
     */
    Estudiantes save(Estudiantes estudiantes);

    /**
     * Updates a estudiantes.
     *
     * @param estudiantes the entity to update.
     * @return the persisted entity.
     */
    Estudiantes update(Estudiantes estudiantes);

    /**
     * Partially updates a estudiantes.
     *
     * @param estudiantes the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Estudiantes> partialUpdate(Estudiantes estudiantes);

    /**
     * Get all the estudiantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Estudiantes> findAll(Pageable pageable);

    /**
     * Get the "id" estudiantes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Estudiantes> findOne(Long id);

    /**
     * Delete the "id" estudiantes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
