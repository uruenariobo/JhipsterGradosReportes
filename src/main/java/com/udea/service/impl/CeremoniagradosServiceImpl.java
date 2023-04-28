package com.udea.service.impl;

import com.udea.domain.Ceremoniagrados;
import com.udea.repository.CeremoniagradosRepository;
import com.udea.service.CeremoniagradosService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ceremoniagrados}.
 */
@Service
@Transactional
public class CeremoniagradosServiceImpl implements CeremoniagradosService {

    private final Logger log = LoggerFactory.getLogger(CeremoniagradosServiceImpl.class);

    private final CeremoniagradosRepository ceremoniagradosRepository;

    public CeremoniagradosServiceImpl(CeremoniagradosRepository ceremoniagradosRepository) {
        this.ceremoniagradosRepository = ceremoniagradosRepository;
    }

    @Override
    public Ceremoniagrados save(Ceremoniagrados ceremoniagrados) {
        log.debug("Request to save Ceremoniagrados : {}", ceremoniagrados);
        return ceremoniagradosRepository.save(ceremoniagrados);
    }

    @Override
    public Ceremoniagrados update(Ceremoniagrados ceremoniagrados) {
        log.debug("Request to update Ceremoniagrados : {}", ceremoniagrados);
        return ceremoniagradosRepository.save(ceremoniagrados);
    }

    @Override
    public Optional<Ceremoniagrados> partialUpdate(Ceremoniagrados ceremoniagrados) {
        log.debug("Request to partially update Ceremoniagrados : {}", ceremoniagrados);

        return ceremoniagradosRepository
            .findById(ceremoniagrados.getId())
            .map(existingCeremoniagrados -> {
                if (ceremoniagrados.getFechaceremonia() != null) {
                    existingCeremoniagrados.setFechaceremonia(ceremoniagrados.getFechaceremonia());
                }
                if (ceremoniagrados.getLimiteinscripcion() != null) {
                    existingCeremoniagrados.setLimiteinscripcion(ceremoniagrados.getLimiteinscripcion());
                }
                if (ceremoniagrados.getLugar() != null) {
                    existingCeremoniagrados.setLugar(ceremoniagrados.getLugar());
                }

                return existingCeremoniagrados;
            })
            .map(ceremoniagradosRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ceremoniagrados> findAll(Pageable pageable) {
        log.debug("Request to get all Ceremoniagrados");
        return ceremoniagradosRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ceremoniagrados> findOne(Long id) {
        log.debug("Request to get Ceremoniagrados : {}", id);
        return ceremoniagradosRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ceremoniagrados : {}", id);
        ceremoniagradosRepository.deleteById(id);
    }
}
