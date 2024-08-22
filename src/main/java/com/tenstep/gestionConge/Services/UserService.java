package com.tenstep.gestionConge.Services;

import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.dto.DemandeDto;
import com.tenstep.gestionConge.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto getUserById(String user_id);

    void deleteUser(String user_id);
    List<UserDto> getAll();
    UserDto updateUser(String user_id , UserDto updatedUser);
     User getConnectedUser();
    Optional<User> getUserByEmail(String email);


}
