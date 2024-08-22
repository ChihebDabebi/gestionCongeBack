package com.tenstep.gestionConge.Services;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.dto.DemandeDto;

import java.util.List;

public interface DemandeService {
    DemandeDto addDemande(DemandeDto demandeDto);
    DemandeDto getDemandeById(String demande_id);

    void deleteDemande(String demande_id);
    List<DemandeDto> getAll();
    DemandeDto updateDemande(String demande_id , DemandeDto updatedDemande);
    DemandeDto accepterDemande(String id);
    DemandeDto RefuserDemande(String id);
    List<Demande> getMyDemands(String id );

}
