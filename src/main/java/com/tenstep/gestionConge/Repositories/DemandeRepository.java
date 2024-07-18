package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemandeRepository extends MongoRepository<Demande,String> {
}
