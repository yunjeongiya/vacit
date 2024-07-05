package com.oursummer.vacit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:https://konkuk-kuit.github.io/KUIT3_Hackathon_Team5-Web/";
    }
}
