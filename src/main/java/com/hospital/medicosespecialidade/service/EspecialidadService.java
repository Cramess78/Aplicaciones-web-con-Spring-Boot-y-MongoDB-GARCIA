package com.hospital.medicosespecialidade.service;

import com.hospital.medicosespecialidade.domain.Especialidad;
import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    Especialidad crear(Especialidad e);
    List<Especialidad> listar();
    void eliminar(String id);
    Optional<Especialidad> obtener(String id);
}
