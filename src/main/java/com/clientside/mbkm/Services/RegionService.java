package com.clientside.mbkm.Services;

//import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.clientside.mbkm.Models.Region;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {

    @Autowired
    private final RestTemplate restTemplate;

    //Get All
    public List<Region> getAll() {

        //"Authorization", "Basic dXNlcjp1c2Vy"

        return restTemplate
                .exchange("http://localhost:8080/region",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Region>>() {
                        })
                .getBody();
    }

    //Get Region ById == Detail
    public Region getRegionById(Integer idRegion) {
        String endPoint = "http://localhost:8080/region/" + idRegion;
        return restTemplate.exchange(
                endPoint, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Region>() {
                }).getBody();
    }

    //Add Region
    public Region create(Region region) {
        return restTemplate
                .exchange("http://localhost:8080/region",
                        HttpMethod.POST,
                        new HttpEntity<>(region),
                        new ParameterizedTypeReference<Region>() {
                        })
                .getBody();
    }

    //Update Region
    public Region updateRegion(Integer id, Region region) {
            return restTemplate.exchange(
                            "http://localhost:8080/region/"
                                            + id, HttpMethod.PUT,
                            new HttpEntity<Region>(region),
                            new ParameterizedTypeReference<Region>() {
                            }).getBody();
    }

    public Region delete(Integer id) {
            return restTemplate
                            .exchange("http://localhost:8080/region/" + "/" + id, HttpMethod.DELETE, null, Region.class)
                            .getBody();
    }

    public Region deleteRegion(Integer idRegion) {
            return restTemplate.exchange(
                            "http://localhost:8080/region/"
                                            + idRegion,
                            HttpMethod.DELETE,
                            null,
                            new ParameterizedTypeReference<Region>() {
                            }).getBody();
    }


}



