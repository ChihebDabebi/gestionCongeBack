package com.tenstep.gestionConge.Services;

import com.tenstep.gestionConge.Models.Demande;

import java.util.List;

public interface DemandeService {
    Demande addDemande(Demande demande);
    Demande getDemandeById(String demande_id);

    void deleteDemande(String demande_id);
    List<Demande> getAll();
    Demande updateDemande(String demande_id , Demande updatedDemande);
}
