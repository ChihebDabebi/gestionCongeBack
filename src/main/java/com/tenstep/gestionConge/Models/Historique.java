package com.tenstep.gestionConge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "historiques")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Historique {
    @Id
    private String historique_id;
    private LocalDate date ;
    private String details;
    private String user_id ;

}
