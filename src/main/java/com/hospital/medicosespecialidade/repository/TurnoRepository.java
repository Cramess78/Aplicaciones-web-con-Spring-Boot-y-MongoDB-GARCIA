package com.hospital.medicosespecialidade.repository;

import com.hospital.medicosespecialidade.domain.Turno;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends MongoRepository<Turno, String> {
    List<Turno> findByMedicoId(String medicoId);
    List<Turno> findByEspecialidadId(String especialidadId);
    List<Turno> findByFechaHoraBetween(LocalDateTime from, LocalDateTime to);
}
