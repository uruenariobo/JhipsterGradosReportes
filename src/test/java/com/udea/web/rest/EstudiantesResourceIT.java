package com.udea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.udea.IntegrationTest;
import com.udea.domain.Estudiantes;
import com.udea.repository.EstudiantesRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EstudiantesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EstudiantesResourceIT {

    private static final String DEFAULT_NOMBREESTUDIANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBREESTUDIANTE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/estudiantes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EstudiantesRepository estudiantesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEstudiantesMockMvc;

    private Estudiantes estudiantes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estudiantes createEntity(EntityManager em) {
        Estudiantes estudiantes = new Estudiantes()
            .nombreestudiante(DEFAULT_NOMBREESTUDIANTE)
            .email(DEFAULT_EMAIL)
            .telefono(DEFAULT_TELEFONO);
        return estudiantes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estudiantes createUpdatedEntity(EntityManager em) {
        Estudiantes estudiantes = new Estudiantes()
            .nombreestudiante(UPDATED_NOMBREESTUDIANTE)
            .email(UPDATED_EMAIL)
            .telefono(UPDATED_TELEFONO);
        return estudiantes;
    }

    @BeforeEach
    public void initTest() {
        estudiantes = createEntity(em);
    }

    @Test
    @Transactional
    void createEstudiantes() throws Exception {
        int databaseSizeBeforeCreate = estudiantesRepository.findAll().size();
        // Create the Estudiantes
        restEstudiantesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estudiantes)))
            .andExpect(status().isCreated());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeCreate + 1);
        Estudiantes testEstudiantes = estudiantesList.get(estudiantesList.size() - 1);
        assertThat(testEstudiantes.getNombreestudiante()).isEqualTo(DEFAULT_NOMBREESTUDIANTE);
        assertThat(testEstudiantes.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEstudiantes.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
    }

    @Test
    @Transactional
    void createEstudiantesWithExistingId() throws Exception {
        // Create the Estudiantes with an existing ID
        estudiantes.setId(1L);

        int databaseSizeBeforeCreate = estudiantesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstudiantesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estudiantes)))
            .andExpect(status().isBadRequest());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNombreestudianteIsRequired() throws Exception {
        int databaseSizeBeforeTest = estudiantesRepository.findAll().size();
        // set the field null
        estudiantes.setNombreestudiante(null);

        // Create the Estudiantes, which fails.

        restEstudiantesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estudiantes)))
            .andExpect(status().isBadRequest());

        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = estudiantesRepository.findAll().size();
        // set the field null
        estudiantes.setEmail(null);

        // Create the Estudiantes, which fails.

        restEstudiantesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estudiantes)))
            .andExpect(status().isBadRequest());

        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTelefonoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estudiantesRepository.findAll().size();
        // set the field null
        estudiantes.setTelefono(null);

        // Create the Estudiantes, which fails.

        restEstudiantesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estudiantes)))
            .andExpect(status().isBadRequest());

        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllEstudiantes() throws Exception {
        // Initialize the database
        estudiantesRepository.saveAndFlush(estudiantes);

        // Get all the estudiantesList
        restEstudiantesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estudiantes.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreestudiante").value(hasItem(DEFAULT_NOMBREESTUDIANTE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)));
    }

    @Test
    @Transactional
    void getEstudiantes() throws Exception {
        // Initialize the database
        estudiantesRepository.saveAndFlush(estudiantes);

        // Get the estudiantes
        restEstudiantesMockMvc
            .perform(get(ENTITY_API_URL_ID, estudiantes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estudiantes.getId().intValue()))
            .andExpect(jsonPath("$.nombreestudiante").value(DEFAULT_NOMBREESTUDIANTE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO));
    }

    @Test
    @Transactional
    void getNonExistingEstudiantes() throws Exception {
        // Get the estudiantes
        restEstudiantesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEstudiantes() throws Exception {
        // Initialize the database
        estudiantesRepository.saveAndFlush(estudiantes);

        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();

        // Update the estudiantes
        Estudiantes updatedEstudiantes = estudiantesRepository.findById(estudiantes.getId()).get();
        // Disconnect from session so that the updates on updatedEstudiantes are not directly saved in db
        em.detach(updatedEstudiantes);
        updatedEstudiantes.nombreestudiante(UPDATED_NOMBREESTUDIANTE).email(UPDATED_EMAIL).telefono(UPDATED_TELEFONO);

        restEstudiantesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEstudiantes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEstudiantes))
            )
            .andExpect(status().isOk());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
        Estudiantes testEstudiantes = estudiantesList.get(estudiantesList.size() - 1);
        assertThat(testEstudiantes.getNombreestudiante()).isEqualTo(UPDATED_NOMBREESTUDIANTE);
        assertThat(testEstudiantes.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEstudiantes.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void putNonExistingEstudiantes() throws Exception {
        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();
        estudiantes.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstudiantesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, estudiantes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estudiantes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEstudiantes() throws Exception {
        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();
        estudiantes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstudiantesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estudiantes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEstudiantes() throws Exception {
        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();
        estudiantes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstudiantesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estudiantes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEstudiantesWithPatch() throws Exception {
        // Initialize the database
        estudiantesRepository.saveAndFlush(estudiantes);

        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();

        // Update the estudiantes using partial update
        Estudiantes partialUpdatedEstudiantes = new Estudiantes();
        partialUpdatedEstudiantes.setId(estudiantes.getId());

        partialUpdatedEstudiantes.email(UPDATED_EMAIL).telefono(UPDATED_TELEFONO);

        restEstudiantesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEstudiantes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEstudiantes))
            )
            .andExpect(status().isOk());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
        Estudiantes testEstudiantes = estudiantesList.get(estudiantesList.size() - 1);
        assertThat(testEstudiantes.getNombreestudiante()).isEqualTo(DEFAULT_NOMBREESTUDIANTE);
        assertThat(testEstudiantes.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEstudiantes.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void fullUpdateEstudiantesWithPatch() throws Exception {
        // Initialize the database
        estudiantesRepository.saveAndFlush(estudiantes);

        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();

        // Update the estudiantes using partial update
        Estudiantes partialUpdatedEstudiantes = new Estudiantes();
        partialUpdatedEstudiantes.setId(estudiantes.getId());

        partialUpdatedEstudiantes.nombreestudiante(UPDATED_NOMBREESTUDIANTE).email(UPDATED_EMAIL).telefono(UPDATED_TELEFONO);

        restEstudiantesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEstudiantes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEstudiantes))
            )
            .andExpect(status().isOk());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
        Estudiantes testEstudiantes = estudiantesList.get(estudiantesList.size() - 1);
        assertThat(testEstudiantes.getNombreestudiante()).isEqualTo(UPDATED_NOMBREESTUDIANTE);
        assertThat(testEstudiantes.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEstudiantes.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void patchNonExistingEstudiantes() throws Exception {
        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();
        estudiantes.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstudiantesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, estudiantes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estudiantes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEstudiantes() throws Exception {
        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();
        estudiantes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstudiantesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estudiantes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEstudiantes() throws Exception {
        int databaseSizeBeforeUpdate = estudiantesRepository.findAll().size();
        estudiantes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstudiantesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(estudiantes))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Estudiantes in the database
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEstudiantes() throws Exception {
        // Initialize the database
        estudiantesRepository.saveAndFlush(estudiantes);

        int databaseSizeBeforeDelete = estudiantesRepository.findAll().size();

        // Delete the estudiantes
        restEstudiantesMockMvc
            .perform(delete(ENTITY_API_URL_ID, estudiantes.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Estudiantes> estudiantesList = estudiantesRepository.findAll();
        assertThat(estudiantesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
