package com.hospital.medicosespecialidade.service.impl;

import com.hospital.medicosespecialidade.domain.Especialidad;
import com.hospital.medicosespecialidade.repository.EspecialidadRepository;
import com.hospital.medicosespecialidade.service.EspecialidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository repo;
    public EspecialidadServiceImpl(EspecialidadRepository repo) {
        this.repo = repo;
    }
    @Override
    public Especialidad crear(Especialidad e) {
        return repo.save(e);
    }

    @Override
    public List<Especialidad> listar() {
        return repo.findAll();
    }
    @Override
    public Optional<Especialidad> obtener(String id) {
        return repo.findById(id);
    }
    @Override
    public void eliminar(String id) {
        repo.deleteById(id);
    }
}
