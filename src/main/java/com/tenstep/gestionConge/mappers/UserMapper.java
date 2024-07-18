package com.tenstep.gestionConge.mappers;

import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.dto.UserDto;


public class UserMapper {
    public static UserDto mapToUserDto(User user ){
        return new UserDto(
                user.getUser_id(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getCin(),
                user.getAge()
        );
    }
    public static User mapToUser(UserDto userDto ){
        return new User(
                userDto.getUser_id(),
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                userDto.getCin(),
                userDto.getAge(),
                null,
                null
        );
    }
}
