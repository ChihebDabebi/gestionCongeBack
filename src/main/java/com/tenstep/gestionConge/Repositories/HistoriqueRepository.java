package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.Historique;
import com.tenstep.gestionConge.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoriqueRepository extends MongoRepository<Historique,String> {

}
