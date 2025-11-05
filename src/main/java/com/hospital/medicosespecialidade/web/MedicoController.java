package com.hospital.medicosespecialidade.web;

import com.hospital.medicosespecialidade.domain.Medico;
import com.hospital.medicosespecialidade.service.EspecialidadService;
import com.hospital.medicosespecialidade.service.MedicoService;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;
    private final EspecialidadService espService;

    public MedicoController(MedicoService service, EspecialidadService espService) {
        this.service = service;
        this.espService = espService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("medicos", service.listar());
        return "medicos/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("medico", new Medico());
        return "medicos/form";
    }

    @PostMapping
    public String guardar(Medico m){
        service.crear(m);
        return "redirect:/medicos";
    }

    @GetMapping("/{id}/asignar")
    public String asignarForm(@PathVariable String id, Model model){
        model.addAttribute("medicoId", id);
        model.addAttribute("especialidades", espService.listar());
        return "asignaciones/form";
    }

    @PostMapping("/{id}/asignar")
    public String asignar(@PathVariable String id, @RequestParam String idEspecialidad){
        service.asignarEspecialidad(id, idEspecialidad);
        return "redirect:/medicos";
    }

    @GetMapping("/{id}/horario")
    public String horarioForm(@PathVariable String id, Model model){
        model.addAttribute("medicoId", id);
        model.addAttribute("dias", DayOfWeek.values());
        return "disponibilidad/form";
    }

    @PostMapping("/{id}/horario")
    public String agregarHorario(@PathVariable String id,
                                 @RequestParam DayOfWeek dia,
                                 @RequestParam String inicio,
                                 @RequestParam String fin){
        service.agregarHorario(id, dia, LocalTime.parse(inicio), LocalTime.parse(fin), true);
        return "redirect:/medicos";
    }
    @PostMapping("/{id}/eliminar")
    public String eliminarMedico(@PathVariable String id,
                                 @RequestHeader(value="Referer", required=false) String referer) {
        service.eliminar(id); // Llama al método del servicio que elimina al médico
        return (referer != null) ? "redirect:" + referer : "redirect:/medicos";
    }


}
