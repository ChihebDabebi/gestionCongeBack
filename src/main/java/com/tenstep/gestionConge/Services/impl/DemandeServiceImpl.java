package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Repositories.DemandeRepository;
import com.tenstep.gestionConge.Services.DemandeService;
import com.tenstep.gestionConge.dto.DemandeDto;
import com.tenstep.gestionConge.mappers.DemandeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DemandeServiceImpl implements DemandeService {


    private final DemandeRepository demandeRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository){
        this.demandeRepository=demandeRepository;
    }
    @Override
    public DemandeDto addDemande(DemandeDto demandeDto) {
        Demande demande = DemandeMapper.mapToDemande(demandeDto);
        demande.setDemande_id(UUID.randomUUID().toString().split("-")[0]);
        demande.setDate(new Date());
        Demande  savedDemande =demandeRepository.save(demande);
        return DemandeMapper.mapToDemandeDto(savedDemande);
    }

    @Override
    public DemandeDto getDemandeById(String demande_id) {
        Demande demande = demandeRepository.findById(demande_id).orElseThrow(() -> new RuntimeException("demande is not exist with given id :  " + demande_id));
        return DemandeMapper.mapToDemandeDto(demande);
    }

    @Override
    public void deleteDemande(String demande_id) {
        demandeRepository.findById(demande_id).orElseThrow(()-> new RuntimeException("aucune demande avec cette id "));
        demandeRepository.deleteById(demande_id);
    }

    @Override
    public List<DemandeDto> getAll() {
        return null;
    }

    @Override
    public DemandeDto updateDemande(String demande_id, DemandeDto updatedDemande) {
        Demande demande = demandeRepository.findById(demande_id).orElseThrow(() -> new RuntimeException("Departement not found with given id  "));
        demande.setMotif(updatedDemande.getMotif());
        demande.setStatus(updatedDemande.getStatus());
        Demande updated = demandeRepository.save(demande);
        return DemandeMapper.mapToDemandeDto(updated);    }
}
