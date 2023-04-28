package com.udea.repository;

import com.udea.domain.Planestudios;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Planestudios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanestudiosRepository extends JpaRepository<Planestudios, Long> {}
