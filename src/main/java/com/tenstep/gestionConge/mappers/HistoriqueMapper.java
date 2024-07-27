package com.tenstep.gestionConge.mappers;

import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.dto.HistoriqueDto;
import org.springframework.beans.factory.annotation.Autowired;

public class HistoriqueMapper {


    public static HistoriqueDto mapToHistoriqueDto(Historique historique){
        return new HistoriqueDto(
                historique.getHistorique_id(),
                null,
                historique.getDetails(),
                historique.getUser_id()

        );
        
    }
    public static Historique mapToHistorique(HistoriqueDto historiqueDto){
        return new Historique(
                historiqueDto.getHistorique_id(),
                historiqueDto.getDate(),
                historiqueDto.getDetails(),
                historiqueDto.getUsername()
        );
    }
}
