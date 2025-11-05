package com.hospital.medicosespecialidade.service;

import com.hospital.medicosespecialidade.domain.Medico;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface DisponibilidadService {
    List<Medico> disponiblesPorEspecialidad(String idEspecialidad, DayOfWeek dia, LocalTime hora);
}
