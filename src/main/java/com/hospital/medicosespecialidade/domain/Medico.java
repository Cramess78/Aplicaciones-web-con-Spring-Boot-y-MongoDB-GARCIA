package com.hospital.medicosespecialidade.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
@Document(collection = "medicos")
public class Medico {
    @Id
    private String id;
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String colegiatura;
    private String telefono;
    @Email
    private String correo;

    private EstadoMedico estado = EstadoMedico.ACTIVO;
    private List<String> especialidadIds;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getColegiatura() { return colegiatura; }
    public void setColegiatura(String colegiatura) { this.colegiatura = colegiatura; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public EstadoMedico getEstado() { return estado; }
    public void setEstado(EstadoMedico estado) { this.estado = estado; }
    public List<String> getEspecialidadIds() { return especialidadIds; }
    public void setEspecialidadIds(List<String> especialidadIds) { this.especialidadIds = especialidadIds; }
}
