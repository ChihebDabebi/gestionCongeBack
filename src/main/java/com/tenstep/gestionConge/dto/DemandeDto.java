package com.tenstep.gestionConge.dto;

import com.tenstep.gestionConge.Models.Response;
import com.tenstep.gestionConge.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDto implements Serializable {
    private String demande_id;
    private LocalDate  date;
    private LocalDate  dateDebut ;
    private LocalDate dateFin ;
    private Response response;
    private String motif;
    private String status;
    private String user;
}
