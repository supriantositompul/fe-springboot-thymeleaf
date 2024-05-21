package com.clientside.mbkm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // * html
// @RestController // * json
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("isActive", "home");
        return "index";
    }

    @GetMapping("/home")
    public String dashboard(Model model) {
        model.addAttribute("isActive", "home");
        return "index";
    }
}