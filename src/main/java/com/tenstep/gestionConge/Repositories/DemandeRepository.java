package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DemandeRepository extends MongoRepository<Demande,String> {
    List<Demande> findAll();
    @Query("{ 'user_id' : ?0 }")
    List<Demande> findByUserId(String user_id);

}
