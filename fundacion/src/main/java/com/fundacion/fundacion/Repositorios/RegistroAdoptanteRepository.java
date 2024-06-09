package com.fundacion.fundacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacion.fundacion.Entidades.RegistroAdoptante;

@Repository
public interface RegistroAdoptanteRepository extends JpaRepository<RegistroAdoptante, Long>{

}


