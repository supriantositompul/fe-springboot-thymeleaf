package com.clientside.mbkm.Controllers.rest;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clientside.mbkm.Services.CountryService;
import lombok.AllArgsConstructor;
import java.util.List;
import com.clientside.mbkm.Models.Country;

@AllArgsConstructor
@RestController
@RequestMapping("/api/country")
public class RestCountryController {

    private CountryService countryService;
    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

     @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @PutMapping("/{id}")
    public Country update(@RequestBody Country country) {
        return countryService.update(country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryService.deleteCountry(id);
    }

}
