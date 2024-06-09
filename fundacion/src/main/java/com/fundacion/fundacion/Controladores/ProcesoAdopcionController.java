package com.fundacion.fundacion.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fundacion.fundacion.Entidades.Animales;
import com.fundacion.fundacion.Entidades.ProcesoAdopcion;
import com.fundacion.fundacion.Entidades.RegistroAdoptante;
import com.fundacion.fundacion.Repositorios.AnimalesRepository;
import com.fundacion.fundacion.Repositorios.ProcesoAdopcionRepository;
import com.fundacion.fundacion.Repositorios.RegistroAdoptanteRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fundacion.fundacion.Entidades.Animales;
import com.fundacion.fundacion.Entidades.ProcesoAdopcion;
import com.fundacion.fundacion.Entidades.RegistroAdoptante;
import com.fundacion.fundacion.Repositorios.AnimalesRepository;
import com.fundacion.fundacion.Repositorios.ProcesoAdopcionRepository;
import com.fundacion.fundacion.Repositorios.RegistroAdoptanteRepository;
import org.apache.poi.ss.usermodel.*;



import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fundacion.fundacion.Entidades.ProcesoAdopcion;
import com.fundacion.fundacion.Repositorios.ProcesoAdopcionRepository;

@Controller
@RequestMapping("/proceso")
public class ProcesoAdopcionController {

    @Autowired
    private ProcesoAdopcionRepository procesoAdopcionRepository;

    @Autowired
    private AnimalesRepository animalRepository;

    @Autowired
    private RegistroAdoptanteRepository registroAdoptanteRepository;

    @GetMapping("/")
    public String getAllProcesos(Model model) {
        model.addAttribute("procesos", procesoAdopcionRepository.findAll());
        return "procesos";
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("procesoAdopcion", new ProcesoAdopcion());
        
        // Obtener la lista de animales y adoptantes
        List<Animales> animales = animalRepository.findAll();
        List<RegistroAdoptante> adoptantes = registroAdoptanteRepository.findAll();
        
        model.addAttribute("animales", animales);
        model.addAttribute("adoptantes", adoptantes);
        
        return "agregar";
    }

    @PostMapping("/add")
    public String addProceso(@ModelAttribute("procesoAdopcion") ProcesoAdopcion procesoAdopcion,
                              @RequestParam("fk_idanimales") long idAnimal,
                              @RequestParam("fk_idregistro_adoptante") long idAdoptante) {
        // Obtener los objetos de Animales y RegistroAdoptante por sus IDs
        Optional<Animales> animal = animalRepository.findById(idAnimal);
        Optional<RegistroAdoptante> adoptante = registroAdoptanteRepository.findById(idAdoptante);
        
        // Verificar si se encontraron los objetos
        if (animal.isPresent() && adoptante.isPresent()) {
            // Asignar los objetos encontrados al proceso de adopción
            procesoAdopcion.setAnimales(animal.get());
            procesoAdopcion.setRegistroAdoptante(adoptante.get());
            
            // Asignar valores predeterminados
            procesoAdopcion.setFecha_fin(null); // Fecha final vacía
            procesoAdopcion.setEstado("Activo"); // Estado predeterminado
            
            // Guardar el proceso de adopción
            procesoAdopcionRepository.save(procesoAdopcion);
            return "redirect:/proceso/";
        } else {
            // Manejar el caso en que no se encuentren los objetos
            return "redirect:/proceso/agregar?error=true";
        }
    }

    @GetMapping("/editar/{id}") // Cambia "/actualizar" a "/editar"
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<ProcesoAdopcion> proceso = procesoAdopcionRepository.findById(id);
        if (proceso.isPresent()) {
            model.addAttribute("procesoAdopcion", proceso.get());
            return "editar"; // Cambia a la página edita.html
        } else {
            model.addAttribute("error", "Proceso de adopción no encontrado");
            return "redirect:/proceso/"; // Redirecciona a la página principal
        }
    }

    @PostMapping("/actualizar/{id}")
    public String updateProceso(@PathVariable("id") int id, @ModelAttribute("procesoAdopcion") ProcesoAdopcion procesoAdopcion, Model model) {
        // Obtener el proceso existente de la base de datos
        Optional<ProcesoAdopcion> procesoExistente = procesoAdopcionRepository.findById((long) id);
        if (procesoExistente.isPresent()) {
            // Asignar las claves foráneas existentes al proceso que se está actualizando
            procesoAdopcion.setAnimales(procesoExistente.get().getAnimales());
            procesoAdopcion.setRegistroAdoptante(procesoExistente.get().getRegistroAdoptante());
            
            // Establecer el ID del proceso para actualizar el registro existente
            procesoAdopcion.setIdproceso_adoptcion(id);
            
            // Guardar el proceso de adopción actualizado
            procesoAdopcionRepository.save(procesoAdopcion);
            return "redirect:/proceso/";
        } else {
            model.addAttribute("error", "Proceso de adopción no encontrado");
            return "proceso/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProceso(@PathVariable("id") long id, Model model) {
        Optional<ProcesoAdopcion> proceso = procesoAdopcionRepository.findById(id);
        if (proceso.isPresent()) {
            procesoAdopcionRepository.delete(proceso.get());
            return "redirect:/proceso/";
        } else {
            model.addAttribute("error", "Proceso de adopción no encontrado");
            return "proceso/list";
        }
    }
    
}