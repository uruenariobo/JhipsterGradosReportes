package com.udea.service;

import com.udea.domain.Planestudios;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Planestudios}.
 */
public interface PlanestudiosService {
    /**
     * Save a planestudios.
     *
     * @param planestudios the entity to save.
     * @return the persisted entity.
     */
    Planestudios save(Planestudios planestudios);

    /**
     * Updates a planestudios.
     *
     * @param planestudios the entity to update.
     * @return the persisted entity.
     */
    Planestudios update(Planestudios planestudios);

    /**
     * Partially updates a planestudios.
     *
     * @param planestudios the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Planestudios> partialUpdate(Planestudios planestudios);

    /**
     * Get all the planestudios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Planestudios> findAll(Pageable pageable);

    /**
     * Get the "id" planestudios.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Planestudios> findOne(Long id);

    /**
     * Delete the "id" planestudios.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
