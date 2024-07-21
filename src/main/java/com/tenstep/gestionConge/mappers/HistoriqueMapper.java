package com.tenstep.gestionConge.mappers;

import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.dto.HistoriqueDto;

public class HistoriqueMapper {

    public static HistoriqueDto mapToHistoriqueDto(Historique historique){
        return new HistoriqueDto(
                historique.getHistorique_id(),
                null,
                historique.getDetails()

        );
        
    }
    public static Historique mapToHistorique(HistoriqueDto historiqueDto){
        return new Historique(
                historiqueDto.getHistorique_id(),
                historiqueDto.getDate(),
                historiqueDto.getDetails(),
                null
        );
    }
}
