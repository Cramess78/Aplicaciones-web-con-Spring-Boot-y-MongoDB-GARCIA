package com.hospital.medicosespecialidade.api;

import com.hospital.medicosespecialidade.domain.*;
import com.hospital.medicosespecialidade.service.MedicoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoRestController {

    private final MedicoService service;
    public MedicoRestController(MedicoService s){ this.service = s; }
    @PostMapping
    public ResponseEntity<Medico> crear(@RequestBody Medico m){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(m));
    }
    @GetMapping
    public List<Medico> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtener(@PathVariable String id){
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public Medico actualizar(@PathVariable String id, @RequestBody Medico m){  // <-- CAMBIO
        return service.actualizar(id, m);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{idMedico}/especialidades/{idEsp}")
    public ResponseEntity<Void> asignar(@PathVariable String idMedico, @PathVariable String idEsp){
        service.asignarEspecialidad(idMedico, idEsp);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{idMedico}/horarios")
    public HorarioMedico agregarHorario(@PathVariable String idMedico,
                                        @RequestParam DayOfWeek dia,
                                        @RequestParam String inicio,
                                        @RequestParam String fin,
                                        @RequestParam(defaultValue = "true") boolean activo){
        return service.agregarHorario(idMedico, dia, LocalTime.parse(inicio), LocalTime.parse(fin), activo);
    }
}

