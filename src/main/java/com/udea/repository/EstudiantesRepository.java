package com.udea.repository;

import com.udea.domain.Estudiantes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Estudiantes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstudiantesRepository extends JpaRepository<Estudiantes, Long> {}
