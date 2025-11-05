package com.hospital.medicosespecialidade.service.impl;

import com.hospital.medicosespecialidade.domain.HorarioMedico;
import com.hospital.medicosespecialidade.domain.Medico;
import com.hospital.medicosespecialidade.repository.HorarioMedicoRepository;
import com.hospital.medicosespecialidade.repository.MedicoRepository;
import com.hospital.medicosespecialidade.service.DisponibilidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DisponibilidadServiceImpl implements DisponibilidadService {

    private final HorarioMedicoRepository horarioRepo;
    private final MedicoRepository medicoRepo;
    public DisponibilidadServiceImpl(HorarioMedicoRepository h, MedicoRepository m) {
        this.horarioRepo = h;
        this.medicoRepo = m;
    }

    @Override
    public List<Medico> disponiblesPorEspecialidad(String idEspecialidad, DayOfWeek dia, LocalTime hora) {
        List<HorarioMedico> horarios = horarioRepo.findByDiaSemanaAndActivo(dia, true);
        return horarios.stream()
                .filter(h -> !hora.isBefore(h.getHoraInicio()) && !hora.isAfter(h.getHoraFin()))
                .map(HorarioMedico::getMedicoId)
                .map(medicoRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(m -> m.getEspecialidadIds() != null && m.getEspecialidadIds().contains(idEspecialidad))
                .distinct()
                .collect(Collectors.toList());
    }

}
