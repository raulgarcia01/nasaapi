package com.nasa.prueba.aspirante.infraestructura.service;

import com.nasa.prueba.aspirante.dominio.entities.NasaDataEntity;

import java.util.List;

public interface NasaDataService {

    public List<NasaDataEntity> getNasaData(String filter, String sort);

    public void saveNasaData(NasaDataEntity data);



}
