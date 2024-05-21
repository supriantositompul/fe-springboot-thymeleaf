package com.clientside.mbkm.Controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientside.mbkm.Services.RegionService;
import com.clientside.mbkm.Models.Region;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/region")
public class RestRegionController {
    private RegionService regionService;

    @GetMapping
    public List<Region> getALL() {
        return regionService.getAll();
    }
    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id) {
        return regionService.getRegionById(id);
    }

    @PostMapping
    public Region create(@RequestBody Region region) {
        return regionService.create(region);
    }

    @PutMapping("/{id}")
    public Region update(@RequestBody Integer id, Region region) {
        return regionService.updateRegion(id, region);
    }

    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id) {
        return regionService.delete(id);
    }

}
