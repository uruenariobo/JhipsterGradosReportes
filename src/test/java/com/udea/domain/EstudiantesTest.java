package com.udea.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EstudiantesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Estudiantes.class);
        Estudiantes estudiantes1 = new Estudiantes();
        estudiantes1.setId(1L);
        Estudiantes estudiantes2 = new Estudiantes();
        estudiantes2.setId(estudiantes1.getId());
        assertThat(estudiantes1).isEqualTo(estudiantes2);
        estudiantes2.setId(2L);
        assertThat(estudiantes1).isNotEqualTo(estudiantes2);
        estudiantes1.setId(null);
        assertThat(estudiantes1).isNotEqualTo(estudiantes2);
    }
}
