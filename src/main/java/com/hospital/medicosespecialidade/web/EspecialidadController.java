package com.hospital.medicosespecialidade.web;

import com.hospital.medicosespecialidade.domain.Especialidad;
import com.hospital.medicosespecialidade.service.DisponibilidadService;
import com.hospital.medicosespecialidade.service.EspecialidadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadController {

    private final EspecialidadService espService;
    private final DisponibilidadService dispService;

    public EspecialidadController(EspecialidadService espService, DisponibilidadService dispService) {
        this.espService = espService;
        this.dispService = dispService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("especialidades", espService.listar());
        model.addAttribute("dias", java.time.DayOfWeek.values());
        return "especialidades/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("especialidad", new Especialidad());
        return "especialidades/form";
    }

    @PostMapping
    public String guardar(Especialidad e){
        espService.crear(e);
        return "redirect:/especialidades";
    }
    @GetMapping("/{id}/disponibles")
    public String disponibles(@PathVariable String id,
                              @RequestParam DayOfWeek dia,
                              @RequestParam String hora,
                              Model model){
        var medicos = dispService.disponiblesPorEspecialidad(id, dia, LocalTime.parse(hora));
        if (medicos == null) medicos = new ArrayList<>();
        model.addAttribute("medicos", medicos);
        model.addAttribute("especialidadId", id);
        model.addAttribute("dia", dia);
        model.addAttribute("hora", hora);
        return "busqueda/disponibles";
    }
    @PostMapping("/{id}/eliminar")
    public String eliminarEspecialidad(@PathVariable String id,
                                       @RequestHeader(value="Referer", required=false) String referer) {
        espService.eliminar(id);
        return (referer != null) ? "redirect:" + referer : "redirect:/especialidades";
    }




}
