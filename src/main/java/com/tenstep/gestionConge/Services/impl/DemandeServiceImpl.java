package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Repositories.DemandeRepository;
import com.tenstep.gestionConge.Services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    DemandeRepository demandeRepository;
    @Override
    public Demande addDemande(Demande demande) {

        demande.setDemande_id(UUID.randomUUID().toString().split("-")[0]);
        return demandeRepository.save(demande);

    }

    @Override
    public Demande getDemandeById(String demande_id) {
        return null;
    }

    @Override
    public void deleteDemande(String demande_id) {

    }

    @Override
    public List<Demande> getAll() {
        return null;
    }

    @Override
    public Demande updateDemande(String demande_id, Demande updatedDemande) {
        return null;
    }
}
