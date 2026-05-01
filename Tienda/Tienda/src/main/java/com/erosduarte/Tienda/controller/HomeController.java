package com.erosduarte.Tienda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {





    @GetMapping("/home")
    public  String home(Model model, Principal principal){
        model.addAttribute("username", principal.getName());
        return "home";
    }
    @GetMapping("/")
    public  String redirectHome(){
        return "redirect:/home";
    }



}