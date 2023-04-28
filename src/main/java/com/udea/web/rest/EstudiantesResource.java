package com.udea.web.rest;

import com.udea.domain.Estudiantes;
import com.udea.repository.EstudiantesRepository;
import com.udea.service.EstudiantesService;
import com.udea.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.udea.domain.Estudiantes}.
 */
@RestController
@RequestMapping("/api")
public class EstudiantesResource {

    private final Logger log = LoggerFactory.getLogger(EstudiantesResource.class);

    private static final String ENTITY_NAME = "estudiantes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstudiantesService estudiantesService;

    private final EstudiantesRepository estudiantesRepository;

    public EstudiantesResource(EstudiantesService estudiantesService, EstudiantesRepository estudiantesRepository) {
        this.estudiantesService = estudiantesService;
        this.estudiantesRepository = estudiantesRepository;
    }

    /**
     * {@code POST  /estudiantes} : Create a new estudiantes.
     *
     * @param estudiantes the estudiantes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estudiantes, or with status {@code 400 (Bad Request)} if the estudiantes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estudiantes")
    public ResponseEntity<Estudiantes> createEstudiantes(@Valid @RequestBody Estudiantes estudiantes) throws URISyntaxException {
        log.debug("REST request to save Estudiantes : {}", estudiantes);
        if (estudiantes.getId() != null) {
            throw new BadRequestAlertException("A new estudiantes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Estudiantes result = estudiantesService.save(estudiantes);
        return ResponseEntity
            .created(new URI("/api/estudiantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estudiantes/:id} : Updates an existing estudiantes.
     *
     * @param id the id of the estudiantes to save.
     * @param estudiantes the estudiantes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estudiantes,
     * or with status {@code 400 (Bad Request)} if the estudiantes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estudiantes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiantes> updateEstudiantes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Estudiantes estudiantes
    ) throws URISyntaxException {
        log.debug("REST request to update Estudiantes : {}, {}", id, estudiantes);
        if (estudiantes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, estudiantes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!estudiantesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Estudiantes result = estudiantesService.update(estudiantes);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estudiantes.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /estudiantes/:id} : Partial updates given fields of an existing estudiantes, field will ignore if it is null
     *
     * @param id the id of the estudiantes to save.
     * @param estudiantes the estudiantes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estudiantes,
     * or with status {@code 400 (Bad Request)} if the estudiantes is not valid,
     * or with status {@code 404 (Not Found)} if the estudiantes is not found,
     * or with status {@code 500 (Internal Server Error)} if the estudiantes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/estudiantes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Estudiantes> partialUpdateEstudiantes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Estudiantes estudiantes
    ) throws URISyntaxException {
        log.debug("REST request to partial update Estudiantes partially : {}, {}", id, estudiantes);
        if (estudiantes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, estudiantes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!estudiantesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Estudiantes> result = estudiantesService.partialUpdate(estudiantes);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estudiantes.getId().toString())
        );
    }

    /**
     * {@code GET  /estudiantes} : get all the estudiantes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estudiantes in body.
     */
    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiantes>> getAllEstudiantes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Estudiantes");
        Page<Estudiantes> page = estudiantesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /estudiantes/:id} : get the "id" estudiantes.
     *
     * @param id the id of the estudiantes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estudiantes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiantes> getEstudiantes(@PathVariable Long id) {
        log.debug("REST request to get Estudiantes : {}", id);
        Optional<Estudiantes> estudiantes = estudiantesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estudiantes);
    }

    /**
     * {@code DELETE  /estudiantes/:id} : delete the "id" estudiantes.
     *
     * @param id the id of the estudiantes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Void> deleteEstudiantes(@PathVariable Long id) {
        log.debug("REST request to delete Estudiantes : {}", id);
        estudiantesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
