package com.hospital.medicosespecialidade.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "medico_especialidad")
public class MedicoEspecialidad {

    @Id
    private String id;
    @DBRef
    private Medico medico;
    @DBRef
    private Especialidad especialidad;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
}
