package com.hospital.medicosespecialidade.repository;

import com.hospital.medicosespecialidade.domain.HorarioMedico;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.DayOfWeek;
import java.util.List;

public interface HorarioMedicoRepository extends MongoRepository<HorarioMedico, String> {
    List<HorarioMedico> findByDiaSemanaAndActivo(DayOfWeek diaSemana, boolean activo);
}
