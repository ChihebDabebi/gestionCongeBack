package com.tenstep.gestionConge.dto;

import com.tenstep.gestionConge.Models.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistoriqueDto {
    private String historique_id;
    private LocalDate date ;
    private String details;
    private String user_id;

    public HistoriqueDto(LocalDate date , String details, String user_id){
        this.date=date;
        this.details = details;
        this.user_id = user_id;
    }
}

