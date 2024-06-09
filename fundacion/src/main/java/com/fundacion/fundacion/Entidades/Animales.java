package com.fundacion.fundacion.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "animales")
public class Animales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idanimales;

    private String nombre;
    private String tipo_mascota;
    private int edad;
    private String raza;
    private String genero;

    @OneToOne(mappedBy = "animales")
    private ProcesoAdopcion procesoAdopcion;

    @OneToOne
    @JoinColumn(name = "idhistorial_clinico")
    private HistorialClinico historialClinico;
    
    // Getters y Setters
    public Long getIdanimales() {
        return idanimales;
    }

    public void setIdanimales(Long idanimales) {
        this.idanimales = idanimales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_mascota() {
        return tipo_mascota;
    }

    public void setTipo_mascota(String tipo_mascota) {
        this.tipo_mascota = tipo_mascota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ProcesoAdopcion getProcesoAdopcion() {
        return procesoAdopcion;
    }

    public void setProcesoAdopcion(ProcesoAdopcion procesoAdopcion) {
        this.procesoAdopcion = procesoAdopcion;
    }

    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }
}