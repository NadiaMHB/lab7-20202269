package org.example.lab720202269.controller;

import org.example.lab720202269.entity.Room;
import org.example.lab720202269.repository.FuncionRepository;
import org.example.lab720202269.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/gerente")
public class GerenteController {

    private final RoomRepository roomRepository;
    private final FuncionRepository funcionRepository;

    public GerenteController(FuncionRepository funcionRepository, RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.funcionRepository = funcionRepository;
    }

    @GetMapping("/salas")
    public String listarSalas(Model model) {
        model.addAttribute("salas", roomRepository.findAll());
        return "gerente/listarSalas";
    }

    @GetMapping("/crearSala")
    public String crearSala(Model model) {
        model.addAttribute("sala", new Room());
        return "gerente/crearSala";
    }

    @PostMapping("/guardarSala")
    public String guardarSala(@ModelAttribute("sala") Room sala) {
        roomRepository.save(sala);
        return "redirect:/gerente/listarSalas";
    }

    @GetMapping("/editarSala")
    public String editarSala(@RequestParam("id") int id, Model model) {
        Optional<Room> salaOpt = roomRepository.findById(id);
        if (salaOpt.isPresent()) {
            model.addAttribute("sala", salaOpt.get());
        } else {
            return "redirect:/gerente/listarSalas";
        }
        return "gerente/editarSala";
    }

    @PostMapping("/eliminarSala")
    public String eliminarSala(@RequestParam("id") int id) {
        roomRepository.deleteById(id);
        return "redirect:/gerente/listarSalas";
    }

    @GetMapping("/listarFunciones")
    public String listarFunciones(Model model) {
        model.addAttribute("funciones", funcionRepository.findAll());
        return "gerente/listarFunciones";
    }
}
