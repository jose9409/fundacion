package com.fundacion.fundacion.Entidades;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "proceso_adopcion")
public class ProcesoAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproceso_adoptcion;

    private Date fecha_inicio;
    private Date fecha_fin;
    private String estado;
    private String descripcion;

    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToOne
    @JoinColumn(name = "fk_idanimales")
    private Animales animales;

    @OneToOne
    @JoinColumn(name = "fk_idregistro_adoptante")
    private RegistroAdoptante registroAdoptante;

    // Getters y Setters
    public int getIdproceso_adoptcion() {
        return idproceso_adoptcion;
    }

    public void setIdproceso_adoptcion(int idproceso_adoptcion) {
        this.idproceso_adoptcion = idproceso_adoptcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Animales getAnimales() {
        return animales;
    }

    public void setAnimales(Animales animales) {
        this.animales = animales;
    }

    public RegistroAdoptante getRegistroAdoptante() {
        return registroAdoptante;
    }

    public void setRegistroAdoptante(RegistroAdoptante registroAdoptante) {
        this.registroAdoptante = registroAdoptante;
    }
}
