package org.example.lab720202269.controller;

import org.example.lab720202269.entity.Funcion;
import org.example.lab720202269.repository.FuncionRepository;
import org.example.lab720202269.repository.ObraRepository;
import org.example.lab720202269.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final FuncionRepository funcionRepository;
    private final ObraRepository obraRepository;
    private final RoomRepository roomRepository;

    public AdminController(FuncionRepository funcionRepository, ObraRepository obraRepository, RoomRepository roomRepository) {
        this.funcionRepository = funcionRepository;
        this.obraRepository = obraRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/funciones")
    public String listarFunciones(Model model) {
        model.addAttribute("funciones", funcionRepository.findAll());
        return "admin/funciones";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("funcion", new Funcion());
        model.addAttribute("listaObras", obraRepository.findAll());
        model.addAttribute("listaSalas", roomRepository.findAll());
        return "admin/crearFuncion";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
        if (funcionRepository.existsById(id)) {
            Funcion funcion = funcionRepository.findById(id).get();
            model.addAttribute("funcion", funcion);
            model.addAttribute("listaObras", obraRepository.findAll());
            model.addAttribute("listaSalas", roomRepository.findAll());
            return "admin/editarFuncion";
        } else {
            redirectAttributes.addFlashAttribute("msg", "La función no existe.");
            return "redirect:/admin/listar";
        }
    }

    @PostMapping("/guardar")
    public String guardarFuncion(@ModelAttribute("funcion") Funcion funcion, RedirectAttributes redirectAttributes) {
        funcionRepository.save(funcion);
        redirectAttributes.addFlashAttribute("msg", "La función se ha guardado correctamente.");
        return "redirect:/admin/listar";
    }

    @PostMapping("/eliminar")
    public String eliminarFuncion(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        if (funcionRepository.existsById(id)) {
            funcionRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("msg", "La función se ha eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("msg", "La función no existe.");
        }
        return "redirect:/admin/listar";
    }
}
