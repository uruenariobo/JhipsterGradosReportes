package com.udea.service;

import com.udea.domain.Ceremoniagrados;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Ceremoniagrados}.
 */
public interface CeremoniagradosService {
    /**
     * Save a ceremoniagrados.
     *
     * @param ceremoniagrados the entity to save.
     * @return the persisted entity.
     */
    Ceremoniagrados save(Ceremoniagrados ceremoniagrados);

    /**
     * Updates a ceremoniagrados.
     *
     * @param ceremoniagrados the entity to update.
     * @return the persisted entity.
     */
    Ceremoniagrados update(Ceremoniagrados ceremoniagrados);

    /**
     * Partially updates a ceremoniagrados.
     *
     * @param ceremoniagrados the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Ceremoniagrados> partialUpdate(Ceremoniagrados ceremoniagrados);

    /**
     * Get all the ceremoniagrados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Ceremoniagrados> findAll(Pageable pageable);

    /**
     * Get the "id" ceremoniagrados.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Ceremoniagrados> findOne(Long id);

    /**
     * Delete the "id" ceremoniagrados.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
