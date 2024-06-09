package com.fundacion.fundacion.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fundacion.fundacion.Entidades.Animales;
import com.fundacion.fundacion.Entidades.HistorialClinico;
import com.fundacion.fundacion.Repositorios.AnimalesRepository;
import com.fundacion.fundacion.Repositorios.HistorialClinicoRepository;

@Controller
@RequestMapping("/animales")
public class AnimalesController {

    @Autowired
    private AnimalesRepository animalesRepository;
    
    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @GetMapping("/")
    public String getAllAnimales(Model model) {
        List<Animales> animales = animalesRepository.findAll();
        model.addAttribute("animales", animales);
        return "listaAnimales";
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("animal", new Animales());
        List<HistorialClinico> historialesClinicos = historialClinicoRepository.findAll();
        model.addAttribute("historialesClinicos", historialesClinicos);
        return "agregarnewanim";
    }

    @PostMapping("/agregar")
    public String addAnimal(@ModelAttribute("animal") Animales animal) {
        animalesRepository.save(animal);
        return "redirect:/animales/";
    }
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<Animales> animal = animalesRepository.findById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "editarAnimal";
        } else {
            model.addAttribute("error", "Animal no encontrado");
            return "redirect:/animales/";
        }
    }

    @PostMapping("/editar/{id}")
    public String updateAnimal(@PathVariable("id") long id, @ModelAttribute("animal") Animales animal) {
        animal.setIdanimales(id);
        animalesRepository.save(animal);
        return "redirect:/animales/";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteAnimal(@PathVariable("id") long id) {
        animalesRepository.deleteById(id);
        return "redirect:/animales/";
    }
}