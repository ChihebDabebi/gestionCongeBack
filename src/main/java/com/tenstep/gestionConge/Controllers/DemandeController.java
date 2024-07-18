package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Services.DemandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeController {

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService){
        this.demandeService=demandeService;
    }
    @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande){
        Demande savedDemande = demandeService.addDemande(demande);
        return new ResponseEntity<>(savedDemande, HttpStatus.CREATED);
    }
}
