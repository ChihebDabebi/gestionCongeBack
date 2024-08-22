package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HistoriqueRepository extends MongoRepository<Historique,String> {
    @Query("{ 'user_id' : ?0 }")
    List<Historique> findByUserId(String user_id);
}
