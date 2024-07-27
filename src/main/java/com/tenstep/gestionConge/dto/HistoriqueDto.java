package com.tenstep.gestionConge.dto;

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
    private String username;

    public HistoriqueDto(LocalDate date , String details,String username){
        this.date=date;
        this.details = details;
        this.username=username;
    }
}

