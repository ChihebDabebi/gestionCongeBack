package com.tenstep.gestionConge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueDto {
    private String historique_id;
    private Date date ;
    private String details;

}

