package com.nasa.prueba.aspirante.infraestructura.restcontroller;

import com.nasa.prueba.aspirante.dominio.entities.NasaDataEntity;
import com.nasa.prueba.aspirante.infraestructura.service.NasaDataService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nasa")
public class NasaDataController {

    private final NasaDataService nasaDataService;

    @Autowired
    public NasaDataController(NasaDataService nasaDataService) {
        this.nasaDataService = nasaDataService;
    }

    @RateLimiter(name = "rateLimiterApi")
    @GetMapping
    public List<NasaDataEntity> getAllData(@RequestParam(required = false) String filter,
                                           @RequestParam(required = false) String sort) {
        return nasaDataService.getNasaData(filter , sort);
    }

}
