package com.hospital.medicosespecialidade.repository;

import com.hospital.medicosespecialidade.domain.Especialidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface EspecialidadRepository extends MongoRepository<Especialidad, String> {
    Optional<Especialidad> findByNombreIgnoreCase(String nombre);
}
