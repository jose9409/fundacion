package com.fundacion.fundacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacion.fundacion.Entidades.Animales;

@Repository
public interface AnimalesRepository extends JpaRepository<Animales, Long>{

}
