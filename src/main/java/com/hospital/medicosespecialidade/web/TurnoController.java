package com.hospital.medicosespecialidade.web;

import com.hospital.medicosespecialidade.domain.*;
import com.hospital.medicosespecialidade.repository.*;
import com.hospital.medicosespecialidade.service.TurnoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.List;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

    private final MedicoRepository medicoRepo;
    private final EspecialidadRepository espRepo;
    private final TurnoRepository turnoRepo;
    private final TurnoService turnoService;

    public TurnoController(MedicoRepository m, EspecialidadRepository e, TurnoRepository t, TurnoService s){
        this.medicoRepo = m;
        this.espRepo = e;
        this.turnoRepo = t;
        this.turnoService = s;
    }
    @GetMapping("/nuevo")
    public String nuevo(@RequestParam String medicoId,
                        @RequestParam(required = false) String hora,
                        Model model) {
        Medico medico = medicoRepo.findById(medicoId).orElseThrow();
        model.addAttribute("medico", medico);
        model.addAttribute("turno", new Turno());
        model.addAttribute("hora", hora != null ? hora : "08:00");
        List<Especialidad> especialidades = espRepo.findAll();
        model.addAttribute("especialidades", especialidades);

        return "turnos/form";
    }

    @PostMapping("/nuevo")
    public String guardarTurno(@ModelAttribute Turno turno,
                               @RequestParam String medicoId,
                               @RequestParam String especialidadId,
                               @RequestParam String fecha,
                               @RequestParam String hora) {

        Medico medico = medicoRepo.findById(medicoId).orElseThrow();
        Especialidad especialidad = espRepo.findById(especialidadId).orElseThrow();

        LocalDateTime fechaHora = LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(hora));

        turno.setMedico(medico);
        turno.setEspecialidad(especialidad);
        turno.setFechaHora(fechaHora);

        turnoRepo.save(turno);

        return "redirect:/turnos";
    }
    @GetMapping
    public String listar(Model model){
        List<Turno> turnos = turnoRepo.findAll();
        model.addAttribute("turnos", turnos);
        return "turnos/list";
    }

    @GetMapping("/medico/{medicoId}")
    public String listarPorMedico(@PathVariable String medicoId, Model model){
        Medico medico = medicoRepo.findById(medicoId).orElseThrow();
        model.addAttribute("turnos", turnoRepo.findByMedicoId(medicoId));
        model.addAttribute("medico", medico);
        return "turnos/list";
    }

    @GetMapping("/especialidad/{especialidadId}")
    public String listarPorEspecialidad(@PathVariable String especialidadId, Model model){
        Especialidad esp = espRepo.findById(especialidadId).orElseThrow();
        model.addAttribute("turnos", turnoRepo.findByEspecialidadId(especialidadId));
        model.addAttribute("especialidad", esp);
        return "turnos/list";
    }


    @GetMapping("/buscar")
    public String buscarRango(
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta,
            Model model) {

        LocalDateTime from;
        LocalDateTime to;
        if(desde == null || desde.isEmpty()) {
            from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0);
        } else {
            from = LocalDate.parse(desde).atStartOfDay();
        }
        if(hasta == null || hasta.isEmpty()) {
            to = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        } else {
            to = LocalDate.parse(hasta).atTime(23, 59, 59);
        }

        model.addAttribute("turnos", turnoRepo.findByFechaHoraBetween(from, to));
        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);

        return "turnos/list";
    }
    @PostMapping("/{id}/eliminar")
    public String eliminarTurno(@PathVariable String id,
                                @RequestHeader(value="Referer", required=false) String referer) {
        turnoRepo.deleteById(id); // ✅ eliminar usando turnoRepo
        return (referer != null) ? "redirect:" + referer : "redirect:/turnos";
    }




}
