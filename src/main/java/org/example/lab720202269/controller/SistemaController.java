package org.example.lab720202269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SistemaController {
    @GetMapping({"/SistemaLogin"})
    public String Login() {
        return "/SistemaLogin";
    }

    @GetMapping({"Registro"})
    public String Registro() {

        return "/Register";
    }
}