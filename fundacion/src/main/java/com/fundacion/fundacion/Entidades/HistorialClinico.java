package com.fundacion.fundacion.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "historial_clinico")
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idhistorial_clinico;

    private Date fecha_ingreso;
    private Date fecha_egreso;
    private String diagnostico;
    private int peso;
    private String medicamentos_alergias;
    private String enfermedades;
    private Time hora_ingreso;
    private Time hora_salida;

    @OneToOne
    @JoinColumn(name = "idpersonal_fundacion")
    private PersonalFundacion personalFundacion;

    @OneToOne(mappedBy = "historialClinico")
    private Animales animales;

    public Long getIdhistorial_clinico() {
        return idhistorial_clinico;
    }

    public void setIdhistorial_clinico(Long idhistorial_clinico) {
        this.idhistorial_clinico = idhistorial_clinico;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(Date fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getMedicamentos_alergias() {
        return medicamentos_alergias;
    }

    public void setMedicamentos_alergias(String medicamentos_alergias) {
        this.medicamentos_alergias = medicamentos_alergias;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public Time getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(Time hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public PersonalFundacion getPersonalFundacion() {
        return personalFundacion;
    }

    public void setPersonalFundacion(PersonalFundacion personalFundacion) {
        this.personalFundacion = personalFundacion;
    }

    public Animales getAnimales() {
        return animales;
    }

    public void setAnimales(Animales animales) {
        this.animales = animales;
    }
}