package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.DemandeRepository;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalculService {

    private final UserRepository userRepository;
    private final DemandeRepository demandeRepository;

    public int numberOfUsers(){
        int count = 0 ;
       List<User> users =  userRepository.findAll();
       for(User user : users ){
           count ++ ;
       }
       return count ;
    }

    public int numberOfDemands(){
        int count = 0 ;
        List<Demande> demandes =  demandeRepository.findAll();
        for(Demande demande : demandes ){
            count ++ ;
        }
        return count ;
    }

    public List<Integer> numberOfDemandsPerStatus(){
        int normalCount = 0 ;
        int urgenteCount = 0 ;
        int tresUrgenteCount = 0 ;
        List<Demande> demandes =  demandeRepository.findAll();
        for(Demande demande : demandes ){
            if(demande.getStatus().equals("Normal")){
                normalCount++;
            }else if (demande.getStatus().equals("Urgente")){
                urgenteCount++;
            }else{
                tresUrgenteCount++;
            }
        }
        List<Integer> list = List.of(normalCount,urgenteCount,tresUrgenteCount);
        return list;
    }

    public Map<String,Integer> getDemandsPerMonth(){
        List<Demande> demandes =  demandeRepository.findAll();
        List<String> months = demandes.stream()
                .map(m-> String.valueOf(m.getDate().getMonth()))
                .toList();
        Map<String,Integer> map = new HashMap<>() ;
        int count = 0 ;
        for(String month : months){
            for(Demande demande : demandes){
                if(String.valueOf(demande.getDate().getMonth()).equals(month)){
                    count++;
                }
            }
            map.put(month,count);
            count = 0 ;
        }
        return map ;
    }
   public int  getNumberOfMyDemandsThisMonth(String id){
       List<Demande> demandes =  demandeRepository.findByUserId(id);
       String actualMonth = String.valueOf(LocalDate.now().getMonth());
       int count  = 0 ;
       for(Demande demande : demandes){
           if(String.valueOf(demande.getDate().getMonth()).equals(actualMonth)){
               count++;

           }
       }
       return count ;

   }

}
