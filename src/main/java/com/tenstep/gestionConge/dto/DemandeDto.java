package com.tenstep.gestionConge.dto;

import com.tenstep.gestionConge.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDto {
    private String demande_id;
    private Date date ;
    private String motif;
    private String status;
}
