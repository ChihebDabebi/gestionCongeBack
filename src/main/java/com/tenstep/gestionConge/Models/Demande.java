package com.tenstep.gestionConge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Document(collection = "demandes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande implements Serializable {
    @Id
    private String demande_id;
    private LocalDate  date;
    private LocalDate  dateDebut ;
    private LocalDate dateFin ;
    private String motif;
    private Response response;
    private String status;
    private String user_id;


}
