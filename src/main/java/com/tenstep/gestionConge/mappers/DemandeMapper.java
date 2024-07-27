package com.tenstep.gestionConge.mappers;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.Response;
import com.tenstep.gestionConge.dto.DemandeDto;
import org.springframework.beans.factory.annotation.Autowired;

public class DemandeMapper {


    public static DemandeDto mapToDemandeDto(Demande demande){
        return new DemandeDto(
                demande.getDemande_id(),
                demande.getDate(),
                demande.getDateDebut(),
                demande.getDateFin(),
                demande.getResponse(),
                demande.getMotif(),
                demande.getStatus()

        );
    }
    public static Demande mapToDemande(DemandeDto demandeDto){
        return new Demande(
                demandeDto.getDemande_id(),
                demandeDto.getDate(),
                demandeDto.getDateDebut(),
                demandeDto.getDateFin(),
                demandeDto.getMotif(),
                Response.PENDING,
                demandeDto.getStatus(),
                null

        );
    }
}
