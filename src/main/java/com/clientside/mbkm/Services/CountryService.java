package com.clientside.mbkm.Services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import com.clientside.mbkm.Models.Country;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CountryService {
    private final RestTemplate restTemplate;

    //Get All
    public List<Country> getAll() {
        return restTemplate
                .exchange("http://localhost:8080/country",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Country>>() {
                        })
                .getBody();
    }

    // Add Country
    public Country create(Country country) {
        return restTemplate
                .exchange("http://localhost:8080/country",
                        HttpMethod.POST,
                        new HttpEntity<>(country),
                        new ParameterizedTypeReference<Country>() {
                        })
                .getBody();
    }

    //Detail and Get By Id
    public Country detail(Integer id) {
        return restTemplate.exchange(
                "http://localhost:8080/country/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }

    public Country getById(Integer id) {
        return restTemplate.exchange(
                "http://localhost:8080/country/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }

    public Country update(Country country) {
        return restTemplate.exchange(
                "http://localhost:8080/country/",
                HttpMethod.PUT,
                new HttpEntity<Country>(country),
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }

//     //Delete Country
//     public void delete(Integer id) {
//         restTemplate.delete("http://localhost:8080/country/" + id);
//     }

    public Country deleteCountry(Integer idCountry) {
        return restTemplate.exchange(
                "http://localhost:8080/country/"
                        + idCountry,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }
}
