package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Services.DemandeService;
import com.tenstep.gestionConge.dto.DemandeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demande")
public class DemandeController {

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService){
        this.demandeService=demandeService;
    }
    @PostMapping
    public ResponseEntity<DemandeDto> createDemande(@RequestBody DemandeDto demandeDto){

        DemandeDto savedDemande = demandeService.addDemande(demandeDto);
        return new ResponseEntity<>(savedDemande, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DemandeDto> getDemandeById(@PathVariable String id ){
        DemandeDto demandeById = demandeService.getDemandeById(id);
        return new ResponseEntity<>(demandeById,HttpStatus.ACCEPTED);
    }
}
