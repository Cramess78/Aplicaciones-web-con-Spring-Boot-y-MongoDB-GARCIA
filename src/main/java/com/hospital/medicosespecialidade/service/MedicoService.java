package com.hospital.medicosespecialidade.service;

import com.hospital.medicosespecialidade.domain.*;
import java.time.*;
import java.util.*;

public interface MedicoService {
    Medico crear(Medico m);
    List<Medico> listar();
    Optional<Medico> obtener(String id);
    Medico actualizar(String id, Medico m);
    void eliminar(String id);
    void asignarEspecialidad(String idMedico, String idEspecialidad); // Cambiado a String
    HorarioMedico agregarHorario(String idMedico, DayOfWeek dia, LocalTime inicio, LocalTime fin, boolean activo);
}
