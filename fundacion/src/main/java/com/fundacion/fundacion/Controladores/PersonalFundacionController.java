package com.fundacion.fundacion.Controladores;
	
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fundacion.fundacion.Entidades.PersonalFundacion;
import com.fundacion.fundacion.Repositorios.PersonalFundacionRepository;

@Controller
@RequestMapping("/personal")	
public class PersonalFundacionController {

    @Autowired
    private PersonalFundacionRepository personalFundacionRepository;

    @GetMapping("/")
    public String getAllPersonal(Model model) {
        List<PersonalFundacion> personalList = personalFundacionRepository.findAll();
        model.addAttribute("personalList", personalList);
        return "listaPersonal"; // Asegúrate de tener la vista correspondiente
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("personal", new PersonalFundacion());
        return "agregarPersonal"; // Asegúrate de tener la vista correspondiente
    }

    @PostMapping("/agregar")
    public String addPersonal(@ModelAttribute("personal") PersonalFundacion personal) {
        personalFundacionRepository.save(personal);
        return "redirect:/personal/";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<PersonalFundacion> personalOptional = personalFundacionRepository.findById(id);
        if (personalOptional.isPresent()) {
            model.addAttribute("personal", personalOptional.get());
            return "editarPersonal"; // Asegúrate de tener la vista correspondiente
        } else {
            model.addAttribute("error", "Personal no encontrado");
            return "redirect:/personal/";
        }
    }

    @PostMapping("/editar/{id}")
    public String updatePersonal(@PathVariable("id") long id, @ModelAttribute("personal") PersonalFundacion personal) {
        personal.setIdpersonal_fundacion(id);
        personalFundacionRepository.save(personal);
        return "redirect:/personal/";
    }

    @GetMapping("/eliminar/{id}")
    public String deletePersonal(@PathVariable("id") long id) {
        personalFundacionRepository.deleteById(id);
        return "redirect:/personal/";
    }
}