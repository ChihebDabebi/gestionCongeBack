package com.tenstep.gestionConge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "demandes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande {
    @Id
    private String demande_id;
    private Date date ;
    private String motif;
    private String status;
    private User user;

}
