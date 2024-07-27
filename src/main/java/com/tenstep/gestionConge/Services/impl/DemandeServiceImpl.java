package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.Response;
import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.DemandeRepository;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.Services.DemandeService;
import com.tenstep.gestionConge.Services.HistoriqueService;
import com.tenstep.gestionConge.dto.DemandeDto;
import com.tenstep.gestionConge.dto.HistoriqueDto;
import com.tenstep.gestionConge.mappers.DemandeMapper;
import com.tenstep.gestionConge.utils.NotificationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DemandeServiceImpl implements DemandeService {

    private final EmailSenderService senderService;
    private final HistoriqueService historiqueService;
    private final DemandeRepository demandeRepository;
    private final UserRepository userRepository;


    public DemandeServiceImpl(DemandeRepository demandeRepository,
                              EmailSenderService senderService,
                              HistoriqueService historiqueService,
                              UserRepository userRepository){
        this.demandeRepository=demandeRepository;
        this.senderService = senderService;
        this.historiqueService=historiqueService;
        this.userRepository=userRepository;
    }
    @Override
    public DemandeDto addDemande(DemandeDto demandeDto) {
        Demande demande = DemandeMapper.mapToDemande(demandeDto);
        demande.setDemande_id(UUID.randomUUID().toString().split("-")[0]);
        demande.setDate(LocalDate.now());
        demande.setUser_id("357d07c2");
        String username = userRepository.findById(demande.getUser_id()).get().getNom();
        historiqueService.addHistorique(new HistoriqueDto(demande.getDate(),"votre demande de congé de "+" "+demande.getDateDebut()+" "+demande.getDateFin(),username));
        if (demande.getDateDebut().isBefore(demande.getDateFin()) && demande.getDateDebut().isAfter(LocalDate.now())) {
            Demande savedDemande = demandeRepository.save(demande);
            return DemandeMapper.mapToDemandeDto(savedDemande);

        }else{
            throw new RuntimeException("date de debut doit etre inferieur a date de fin");
        }
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
        List<Demande> demandes = demandeRepository.findAll();
        return demandes.stream()
                .map(e->DemandeMapper.mapToDemandeDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public DemandeDto updateDemande(String demande_id, DemandeDto updatedDemande) {
        Demande demande = demandeRepository.findById(demande_id).orElseThrow(() -> new RuntimeException("Departement not found with given id  "));
        demande.setMotif(updatedDemande.getMotif());
        demande.setStatus(updatedDemande.getStatus());
        Demande updated = demandeRepository.save(demande);
        return DemandeMapper.mapToDemandeDto(updated);
    }

    @Override
    public DemandeDto accepterDemande(String id) {
        Optional<Demande> demandeOpt = demandeRepository.findById(id);
        if(demandeOpt.isPresent()){
            Demande demande=demandeOpt.get();
            demande.setResponse(Response.APPROVED);
            demandeRepository.save(demande);
            User user =  userRepository.findById(demande.getUser_id()).get();
            NotificationMail.sendAccepted(senderService,user.getEmail());
            return DemandeMapper.mapToDemandeDto(demande);
        }else{
            throw new RuntimeException("Demande non trouvé");
        }
    }

    @Override
    public DemandeDto RefuserDemande(String id) {
        Optional<Demande> demandeOpt = demandeRepository.findById(id);
        if(demandeOpt.isPresent()){
            Demande demande=demandeOpt.get();
            demande.setResponse(Response.REJECTED);
            demandeRepository.save(demande);
            User user =  userRepository.findById(demande.getUser_id()).get();
            NotificationMail.sendRefused(senderService,user.getEmail());
            return DemandeMapper.mapToDemandeDto(demande);
        }else{
            throw new RuntimeException("Demande non trouvé");
        }
    }



}
