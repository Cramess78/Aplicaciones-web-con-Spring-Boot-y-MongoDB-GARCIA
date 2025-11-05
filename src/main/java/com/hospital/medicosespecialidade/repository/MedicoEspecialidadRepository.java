package com.hospital.medicosespecialidade.repository;

import com.hospital.medicosespecialidade.domain.MedicoEspecialidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoEspecialidadRepository extends MongoRepository<MedicoEspecialidad, String> {

    List<MedicoEspecialidad> findByEspecialidadId(String especialidadId);
    List<MedicoEspecialidad> findByMedicoId(String medicoId);
}
