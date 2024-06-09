package com.fundacion.fundacion.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fundacion.fundacion.Entidades.RegistroAdoptante;
import com.fundacion.fundacion.Repositorios.RegistroAdoptanteRepository;

@Controller
@RequestMapping("/adoptantes")
public class RegistroAdoptanteController {

    @Autowired
    private RegistroAdoptanteRepository registroAdoptanteRepository;

    @GetMapping("/")
    public String getAllAdoptantes(Model model) {
        List<RegistroAdoptante> adoptantes = registroAdoptanteRepository.findAll();
        model.addAttribute("adoptantes", adoptantes);
        return "listaAdoptantes";
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("adoptante", new RegistroAdoptante());
        return "agregarAdoptante";
    }

    @PostMapping("/agregar")
    public String addAdoptante(@ModelAttribute("adoptante") RegistroAdoptante adoptante) {
        registroAdoptanteRepository.save(adoptante);
        return "redirect:/adoptantes/";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<RegistroAdoptante> optionalAdoptante = registroAdoptanteRepository.findById((long) id);
        if (optionalAdoptante.isPresent()) {
            RegistroAdoptante adoptante = optionalAdoptante.get();
            model.addAttribute("adoptante", adoptante);
            return "editarAdoptante";
        } else {
            model.addAttribute("error", "Adoptante no encontrado");
            return "redirect:/adoptantes/";
        }
    }

    @PostMapping("/editar/{id}")
    public String updateAdoptante(@PathVariable("id") int id, @ModelAttribute("adoptante") RegistroAdoptante adoptante) {
        adoptante.setIdregistro_adoptante(id);
        registroAdoptanteRepository.save(adoptante);
        return "redirect:/adoptantes/";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteAdoptante(@PathVariable("id") int id) {
        registroAdoptanteRepository.deleteById((long) id);
        return "redirect:/adoptantes/";
    }
}