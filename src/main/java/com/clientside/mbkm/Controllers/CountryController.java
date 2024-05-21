package com.clientside.mbkm.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clientside.mbkm.Models.Country;
import com.clientside.mbkm.Services.CountryService;
import com.clientside.mbkm.Services.RegionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;
    private RegionService regionService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("showCountry", countryService.getAll());
        return "country/index";
    }

    // End point : Create/post
    @GetMapping("/create")
    public String createView(Model model) {
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("country", new Country());
        return "country/create-form";
    }

    @PostMapping
    public String create(Country country) {
        countryService.create(country);
        return "redirect:/country";
    }

    // End point : GetById === Detail
    @GetMapping("/detail/{id}")
    public String detailView(@PathVariable Integer id, Model model) {
        model.addAttribute("detail", countryService.detail(id));
        return "country/detail";
    }

    // // End point : GetById === Detail
    // @GetMapping("/{id}")
    // public String getCountryById(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAtt) {
    //     try {
    //         model.addAttribute("detail", countryService.getCountryById(id));
    //     } catch (Exception e) {
    //         redirectAtt.addFlashAttribute("error", "Cannot Find Country");
    //         return "redirect:/country";
    //     }
    //     return "country/detail";
    // }


    @GetMapping("/update/{id}")
    public String updateView(@PathVariable Integer id, Model model) {
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("country", countryService.getById(id));
        return "country/update";
    }

    @PostMapping("/update")
    public String update(Country country) {
        countryService.update(country);
        return "redirect:/country";
    }

    @GetMapping("/delete/{id}")
    public String deleteView(@PathVariable Integer id, Model model) {
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "country/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        countryService.deleteCountry(id);
        return "redirect:/country";
    }
}
