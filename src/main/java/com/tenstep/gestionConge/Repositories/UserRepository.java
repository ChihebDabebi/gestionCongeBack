package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
