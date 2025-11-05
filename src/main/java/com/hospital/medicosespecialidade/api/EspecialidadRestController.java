package com.hospital.medicosespecialidade.api;

import com.hospital.medicosespecialidade.domain.*;
import com.hospital.medicosespecialidade.service.*;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadRestController {

    private final EspecialidadService espService;
    private final DisponibilidadService dispService;

    public EspecialidadRestController(EspecialidadService e, DisponibilidadService d){
        this.espService = e;
        this.dispService = d;
    }
    @PostMapping
    public Especialidad crear(@RequestBody Especialidad e){
        return espService.crear(e);
    }
    @GetMapping
    public List<Especialidad> listar(){
        return espService.listar();
    }
    @GetMapping("/{idEsp}/medicos/disponibles")
    public List<Medico> disponibles(@PathVariable String idEsp,
                                    @RequestParam DayOfWeek dia,
                                    @RequestParam String hora){
        return dispService.disponiblesPorEspecialidad(idEsp, dia, LocalTime.parse(hora));
    }

}
