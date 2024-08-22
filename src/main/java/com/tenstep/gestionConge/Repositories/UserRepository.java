package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);


}
