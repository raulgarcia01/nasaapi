package com.nasa.prueba.aspirante.aplicacion.taskscheduler;

import com.nasa.prueba.aspirante.dominio.dto.ItemDto;
import com.nasa.prueba.aspirante.dominio.dto.RequestDto;
import com.nasa.prueba.aspirante.dominio.entities.NasaDataEntity;
import com.nasa.prueba.aspirante.infraestructura.clientrest.ClientRestService;
import com.nasa.prueba.aspirante.infraestructura.service.NasaDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskExecution {

    Logger logger = LoggerFactory.getLogger(TaskExecution.class);

    @Value("${nasa.api.default.value}")
    private String value;

    private final ClientRestService clientService;

    private final NasaDataService nasaDataService;

    @Autowired
    public TaskExecution(ClientRestService clientService, NasaDataService nasaDataService) {
        this.clientService = clientService;
        this.nasaDataService = nasaDataService;
    }

    @Async
    @Scheduled(cron = "0 * * * * *")
    public void runAsyncTask() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("Ejecutando tarea asincrona a las: " + dateFormat.format(currentDate));
        RequestDto request = clientService.getNasaApiData(value);
            for (ItemDto items : request.getCollection().getItems()) {
                NasaDataEntity nasaData = NasaDataEntity.builder()
                        .href(items.getHref())
                        .center(items.getData().get(0).getCenter())
                        .title(items.getData().get(0).getTitle())
                        .nasaId(items.getData().get(0).getNasa_id())
                        .created_at(new Date())
                        .build();
                nasaDataService.saveNasaData(nasaData);
            }
            logger.info("Finaliza ejecucion");
        }

}
