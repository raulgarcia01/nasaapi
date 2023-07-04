package com.nasa.prueba.aspirante.infraestructura.clientrest;

import com.nasa.prueba.aspirante.dominio.dto.RequestDto;

public interface ClientRestService {

    public RequestDto getNasaApiData(String paramValue);
}
