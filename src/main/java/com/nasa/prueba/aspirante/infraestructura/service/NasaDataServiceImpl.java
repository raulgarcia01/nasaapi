package com.nasa.prueba.aspirante.infraestructura.service;

import com.nasa.prueba.aspirante.dominio.entities.NasaDataEntity;
import com.nasa.prueba.aspirante.infraestructura.repository.NasaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NasaDataServiceImpl implements NasaDataService {

    Logger logger = LoggerFactory.getLogger(NasaDataServiceImpl.class);

    private final NasaDataRepository nasaDataRepo;

    @Autowired
    public NasaDataServiceImpl(NasaDataRepository nasaDataRepo) {
        this.nasaDataRepo = nasaDataRepo;
    }

    @Override
    public List<NasaDataEntity> getNasaData(String filter, String sort) {

        List<NasaDataEntity> data = nasaDataRepo.findAll();
        data.sort(Comparator.comparing(NasaDataEntity::getId).reversed());

        if (filter != null && !filter.isEmpty()) {
            try {
                long filterId = Long.parseLong(filter);
                data = data.stream()
                        .filter(user -> user.getId() == filterId)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                logger.error("Wrong parameter: " + e.getMessage());
            }
        }

        if (sort != null && !sort.isEmpty()) {
            if (sort.equalsIgnoreCase("asc")) {
                data.sort(Comparator.comparing(NasaDataEntity::getId));
            }
        }

        return data;
    }

    @Override
    public void saveNasaData(NasaDataEntity data) {
        nasaDataRepo.save(data);
    }
}
