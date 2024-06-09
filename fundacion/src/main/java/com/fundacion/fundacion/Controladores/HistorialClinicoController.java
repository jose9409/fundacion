package com.fundacion.fundacion.Controladores;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fundacion.fundacion.Entidades.HistorialClinico;
import com.fundacion.fundacion.Entidades.PersonalFundacion;
import com.fundacion.fundacion.Repositorios.HistorialClinicoRepository;
import com.fundacion.fundacion.Repositorios.PersonalFundacionRepository;

@Controller
@RequestMapping("/historial")
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @Autowired
    private PersonalFundacionRepository personalFundacionRepository;
    
    @GetMapping("/")
    public String getAllHistoriales(Model model) {
        List<HistorialClinico> historiales = historialClinicoRepository.findAll();
        model.addAttribute("historiales", historiales);
        return "listaHistoriales";  
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("historial", new HistorialClinico());
        List<PersonalFundacion> personalFundacionList = personalFundacionRepository.findAll();
        model.addAttribute("personalFundacionList", personalFundacionList);
        return "agregarHistorial"; 
    }

    @PostMapping("/agregar")
    public String addHistorial(@ModelAttribute("historial") HistorialClinico historial) {
        historialClinicoRepository.save(historial);
        return "redirect:/historial/";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<HistorialClinico> historialOptional = historialClinicoRepository.findById(id);
        if (historialOptional.isPresent()) {
            HistorialClinico historial = historialOptional.get();
            model.addAttribute("historial", historial);
            
            // Obtener la lista de personalFundacion
            List<PersonalFundacion> personalFundacionList = personalFundacionRepository.findAll();
            model.addAttribute("personalFundacionList", personalFundacionList);
            
            return "editarHistorial";  
        } else {
            model.addAttribute("error", "Historial clínico no encontrado");
            return "redirect:/historial/";
        }
    }

    @PostMapping("/editar/{id}")
    public String updateHistorial(@PathVariable("id") long id, @ModelAttribute("historial") HistorialClinico historial) {
        historial.setIdhistorial_clinico(id);
        historialClinicoRepository.save(historial);
        return "redirect:/historial/";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteHistorial(@PathVariable("id") long id) {
        historialClinicoRepository.deleteById(id);
        return "redirect:/historial/";
    }
}