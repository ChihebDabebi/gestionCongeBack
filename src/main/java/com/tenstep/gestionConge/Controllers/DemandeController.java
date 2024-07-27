package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Services.DemandeService;
import com.tenstep.gestionConge.Services.impl.EmailSenderService;
import com.tenstep.gestionConge.dto.DemandeDto;
import com.tenstep.gestionConge.utils.NotificationMail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demande")
public class DemandeController {
    private final EmailSenderService senderService;
    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService,EmailSenderService senderService) {
        this.demandeService = demandeService;
        this.senderService=senderService;
    }

    @PostMapping("/add")
    public ResponseEntity<DemandeDto> createDemande(@RequestBody DemandeDto demandeDto) {

        DemandeDto savedDemande = demandeService.addDemande(demandeDto);
        return new ResponseEntity<>(savedDemande, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeDto> getDemandeById(@PathVariable String id) {
        DemandeDto demandeById = demandeService.getDemandeById(id);
        return new ResponseEntity<>(demandeById, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
        demandeService.deleteDemande(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<DemandeDto> updateDemande(
            @PathVariable String id,
            @RequestBody DemandeDto demandeDto
    ){
        demandeService.updateDemande(id,demandeDto);
        return new ResponseEntity<>(demandeDto,HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<DemandeDto>> getDemandes(){
        return new ResponseEntity<>(demandeService.getAll(),HttpStatus.ACCEPTED);

    }
    @PutMapping("/{id}/accept")
    public DemandeDto acceptDemande(@PathVariable String id) {
        return demandeService.accepterDemande(id);
    }
    @PutMapping("/{id}/refuse")
    public DemandeDto refuserDemande(@PathVariable String id) {
        return demandeService.accepterDemande(id);
    }

}
