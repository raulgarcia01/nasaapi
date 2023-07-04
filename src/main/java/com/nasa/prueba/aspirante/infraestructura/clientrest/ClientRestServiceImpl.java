package com.nasa.prueba.aspirante.infraestructura.clientrest;

import com.nasa.prueba.aspirante.dominio.dto.RequestDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientRestServiceImpl  implements ClientRestService{

    private static final String API_URL = "https://images-api.nasa.gov/search?q={paramValue}";

    private final RestTemplate restTemplate;

    public ClientRestServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public RequestDto getNasaApiData(String paramValue) {
        ResponseEntity<RequestDto> response = restTemplate.exchange(API_URL, HttpMethod.GET, null, RequestDto.class, paramValue);
        return response.getBody();
    }
}
