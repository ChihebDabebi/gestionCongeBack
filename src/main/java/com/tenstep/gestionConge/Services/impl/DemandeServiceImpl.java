package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.Response;
import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.DemandeRepository;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.Services.DemandeService;
import com.tenstep.gestionConge.Services.HistoriqueService;
import com.tenstep.gestionConge.Services.UserService;
import com.tenstep.gestionConge.dto.DemandeDto;
import com.tenstep.gestionConge.dto.HistoriqueDto;
import com.tenstep.gestionConge.mappers.DemandeMapper;
import com.tenstep.gestionConge.utils.EmailNotificationTask;
import com.tenstep.gestionConge.utils.EmailNotificationTaskRejected;
import com.tenstep.gestionConge.utils.NotificationMail;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class DemandeServiceImpl implements DemandeService {
    private static final Logger logger = LoggerFactory.getLogger(DemandeServiceImpl.class);


    private final EmailSenderService senderService;
    private final HistoriqueService historiqueService;
    private final DemandeRepository demandeRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final HolidaysService holidaysService;


    public DemandeServiceImpl(DemandeRepository demandeRepository,
                              EmailSenderService senderService,
                              HistoriqueService historiqueService,
                              UserRepository userRepository,
                              UserService userService,
                              HolidaysService holidaysService){
        this.demandeRepository=demandeRepository;
        this.senderService = senderService;
        this.historiqueService=historiqueService;
        this.userRepository=userRepository;
        this.userService=userService;
        this.holidaysService = holidaysService;
    }
    @Override
    public DemandeDto addDemande(DemandeDto demandeDto) {
        Demande demande = DemandeMapper.mapToDemande(demandeDto);
        demande.setDemande_id(UUID.randomUUID().toString().split("-")[0]);
        demande.setDate(LocalDate.now());
        int nbrDeJoursDeConge = (int) ChronoUnit.DAYS.between(demande.getDateDebut(),demande.getDateFin());
        User user = userService.getConnectedUser();
        logger.info("user connected: {}", user);
        user.setNbrJours(user.getNbrJours()-nbrDeJoursDeConge);

        String user_id = user.getUser_id();
        demande.setUser_id(user_id);
        historiqueService.addHistorique(new HistoriqueDto(demande.getDate(),"votre demande de congé de "+" "+demande.getDateDebut()+" "+demande.getDateFin(),user_id));
        logger.info("user connected: {}", user);

        if (holidaysService.validateDates(demande.getDateDebut(),demande.getDateFin())
                && nbrDeJoursDeConge < user.getNbrJours()) {
            Demande savedDemande = demandeRepository.save(demande);
            userRepository.save(user);

            return DemandeMapper.mapToDemandeDto(savedDemande);

        }else{
            throw new RuntimeException("holidays and weekends shouldnt be included and respect your count days ");
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
        return demandes.stream().map(demande -> {
            User user = userRepository.findById(demande.getUser_id()).orElseThrow();
            String userNom = user.getNom();

            return new DemandeDto(
                    demande.getDemande_id(),
                    demande.getDate(),
                    demande.getDateDebut(),
                    demande.getDateFin(),
                    demande.getResponse(),
                    demande.getMotif(),
                    demande.getStatus(),
                    userNom
            );
        }).collect(Collectors.toList());
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
            User user =  userRepository.findById(demande.getUser_id()).orElseThrow(()->new RuntimeException("user nexiste pas"));

            demandeRepository.save(demande);
            userRepository.save(user);
            Thread emailThread = new Thread(new EmailNotificationTask(senderService,user.getEmail()));
            emailThread.start();
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
            User user =  userRepository.findById(demande.getUser_id()).orElseThrow(()->new RuntimeException("user nexiste pas"));

            int nbrDeJoursDeConge = (int) ChronoUnit.DAYS.between(demande.getDateDebut(),demande.getDateFin());
            user.setNbrJours(user.getNbrJours()+nbrDeJoursDeConge);
            userRepository.save(user);
            demandeRepository.save(demande);
            Thread emailThread = new Thread(new EmailNotificationTaskRejected(senderService,user.getEmail()));
            emailThread.start();            return DemandeMapper.mapToDemandeDto(demande);
        }else{
            throw new RuntimeException("Demande non trouvé");
        }
    }

    @Override
    public List <Demande> getMyDemands(String id) {
        List<Demande> demandes = demandeRepository.findByUserId(id);

        return demandes;
    }


}
