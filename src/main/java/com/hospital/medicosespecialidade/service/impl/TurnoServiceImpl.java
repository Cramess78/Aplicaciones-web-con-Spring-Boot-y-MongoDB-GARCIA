package com.hospital.medicosespecialidade.service.impl;

import com.hospital.medicosespecialidade.domain.Turno;
import com.hospital.medicosespecialidade.repository.TurnoRepository;
import com.hospital.medicosespecialidade.service.TurnoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TurnoServiceImpl implements TurnoService {
    private final TurnoRepository repo;

    public TurnoServiceImpl(TurnoRepository repo){ this.repo = repo; }

    @Override
    public Turno crear(Turno t){ return repo.save(t); }
}


