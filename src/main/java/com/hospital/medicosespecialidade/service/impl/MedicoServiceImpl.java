package com.hospital.medicosespecialidade.service.impl;

import com.hospital.medicosespecialidade.domain.*;
import com.hospital.medicosespecialidade.repository.*;
import com.hospital.medicosespecialidade.service.MedicoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.*;

@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepo;
    private final EspecialidadRepository espRepo;
    private final HorarioMedicoRepository horarioRepo;

    public MedicoServiceImpl(MedicoRepository m, EspecialidadRepository e, HorarioMedicoRepository h){
        this.medicoRepo = m;
        this.espRepo = e;
        this.horarioRepo = h;
    }

    @Override
    public Medico crear(Medico m) { return medicoRepo.save(m); }

    @Override
    public List<Medico> listar() { return medicoRepo.findAll(); }

    @Override
    public Optional<Medico> obtener(String id) { return medicoRepo.findById(id); }

    @Override
    public Medico actualizar(String id, Medico m){
        return medicoRepo.findById(id).map(existing -> {
            existing.setNombres(m.getNombres());
            existing.setApellidos(m.getApellidos());
            existing.setColegiatura(m.getColegiatura());
            existing.setCorreo(m.getCorreo());
            existing.setTelefono(m.getTelefono());
            existing.setEstado(m.getEstado());
            return medicoRepo.save(existing);
        }).orElseThrow(() -> new NoSuchElementException("Médico no encontrado"));
    }

    @Override
    public void eliminar(String id) { medicoRepo.deleteById(id); }
    @Override
    public void asignarEspecialidad(String idMedico, String idEspecialidad){
    }

    @Override
    public HorarioMedico agregarHorario(String idMedico, DayOfWeek dia, LocalTime inicio, LocalTime fin, boolean activo){
        HorarioMedico h = new HorarioMedico();
        h.setMedicoId(idMedico);
        h.setDiaSemana(dia);
        h.setHoraInicio(inicio);
        h.setHoraFin(fin);
        h.setActivo(activo);
        return horarioRepo.save(h);
    }
}
