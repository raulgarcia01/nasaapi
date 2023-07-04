package com.nasa.prueba.aspirante.dominio.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "api_data")
@Builder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NasaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String href;

    private String center;

    private String title;

    @Column(name = "nasa_id")
    private String nasaId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

}
