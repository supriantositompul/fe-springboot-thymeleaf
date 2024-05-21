package com.clientside.mbkm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clientside.mbkm.Models.Region;
import com.clientside.mbkm.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/region")
public class RegionControllerThy {

    @Autowired
    private RegionService regionService;

    //End point : Get All
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("regions", regionService.getAll());
        return "region/index";
    }

    //End point : Create/post
    @GetMapping("/create")
    public String createView(Region region) {
        return "region/create-form";
    }

    @PostMapping
    public String create(Region region) {
        regionService.create(region);
        return "redirect:/region";
    }

    //End point : GetById === Detail
    @GetMapping("/{id}")
    public String getRegionById(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAtt) {
        try {
            model.addAttribute("region", regionService.getRegionById(id));
        } catch (Exception e) {
            redirectAtt.addFlashAttribute("error", "Cannot Find Region");
            return "redirect:/region";
        }
        return "region/detail";
    }

    //End point : Update
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAtt) {
        try {
            model.addAttribute("region", regionService.getRegionById(id));
        } catch (Exception e) {
            redirectAtt.addFlashAttribute("error", "Cannot Find Region");
            return "redirect:/region";
        }
        return "region/update";
    }

    @PostMapping("/update")
    public String updateRegion(Region region, RedirectAttributes redirectAtt) {
        try {
            Region updateRegion = regionService.updateRegion(region.getId(), region);
            redirectAtt.addFlashAttribute("success", "Region " + updateRegion.getName() + " Updated");
        } catch (Exception e) {
            redirectAtt.addFlashAttribute("error", "Duplicate Data");
        }
        return "redirect:/region";
    }

    //End point : Delete
    @GetMapping("/delete/{id}")
    public String deleteRegion(@PathVariable("id") Integer idRegion, RedirectAttributes redirectAtt) {
        try {
            Region region = regionService.deleteRegion(idRegion);
            redirectAtt.addFlashAttribute("success", "Region " + region.getName() + " Deleted");
        } catch (Exception e) {
            redirectAtt.addFlashAttribute("error", "Cannot Delete");
        }
        return "redirect:/region";
    }
}
