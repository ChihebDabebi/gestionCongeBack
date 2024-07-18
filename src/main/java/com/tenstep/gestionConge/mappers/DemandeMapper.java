package com.tenstep.gestionConge.mappers;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.dto.DemandeDto;
import org.springframework.beans.factory.annotation.Autowired;

public class DemandeMapper {


    public static DemandeDto mapToDemandeDto(Demande demande){
        return new DemandeDto(
                demande.getDemande_id(),
                demande.getDate(),
                demande.getMotif(),
                demande.getStatus()

        );
    }
    public static Demande mapToDemande(DemandeDto demandeDto){
        return new Demande(
                demandeDto.getDemande_id(),
                demandeDto.getDate(),
                demandeDto.getMotif(),
                demandeDto.getStatus(),
                null

        );
    }
}
