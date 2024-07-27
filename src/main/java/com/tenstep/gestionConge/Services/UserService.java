package com.tenstep.gestionConge.Services;

import com.tenstep.gestionConge.dto.DemandeDto;
import com.tenstep.gestionConge.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto getUserById(String user_id);

    void deleteUser(String user_id);
    List<UserDto> getAll();
    UserDto updateUser(String user_id , UserDto updatedUser);

}
