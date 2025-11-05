package com.hospital.medicosespecialidade.repository;

import com.hospital.medicosespecialidade.domain.Medico;
import com.hospital.medicosespecialidade.domain.EstadoMedico;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MedicoRepository extends MongoRepository<Medico, String> {
    List<Medico> findByEstado(EstadoMedico estado);
}
