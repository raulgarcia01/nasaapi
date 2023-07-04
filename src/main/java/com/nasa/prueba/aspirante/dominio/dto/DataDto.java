package com.nasa.prueba.aspirante.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDto {
    private String center;
    private String title;
    private List<String> keywords;
    private String nasa_id;
    private String date_created;
    private String media_type;
    private String description;
}
