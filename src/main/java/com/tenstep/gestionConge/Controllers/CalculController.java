package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Services.impl.CalculService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calcul")
@RequiredArgsConstructor
public class CalculController {

    private final CalculService calculService;
    @GetMapping("/users")
    public int usersNumber () {
        int users = calculService.numberOfUsers();
        return users ;
    }
    @GetMapping("/demandes")
    public int demandesNumber () {
        int demandes = calculService.numberOfDemands();
        return demandes ;
    }
    @GetMapping("/perStatus")
    public List<Integer> demandesNumberPerStatus () {
        List<Integer> numberOfDemandes = calculService.numberOfDemandsPerStatus();
        return numberOfDemandes ;
    }
    @GetMapping("/perMonth")
    public Map<String,Integer> demandesNumberPerMonth () {
        Map<String,Integer> numberOfDemandes = calculService.getDemandsPerMonth();
        return numberOfDemandes ;
    }
    @GetMapping("/myDemand/{id}")
    public int myDemandsPerMonth(@PathVariable String id){
        return calculService.getNumberOfMyDemandsThisMonth(id);
    }


}
