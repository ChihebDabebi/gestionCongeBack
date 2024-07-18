package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Demande;
import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.Services.UserService;
import com.tenstep.gestionConge.dto.UserDto;
import com.tenstep.gestionConge.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDto addUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setUser_id(UUID.randomUUID().toString().split("-")[0]);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(String user_id) {
        User user = userRepository.findById(user_id).orElseThrow(()->new RuntimeException("user avec cet id nexiste pas"));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public void deleteUser(String user_id) {
        User user = userRepository.findById(user_id).orElseThrow(()->new RuntimeException("user nexiste pas"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User>  users = userRepository.findAll();
        return users.stream()
                .map(e->UserMapper.mapToUserDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String user_id, UserDto updatedUser) {
        User user = userRepository.findById(user_id).orElseThrow(()->new RuntimeException("user nexiste pas"));
        user.setNom(updatedUser.getNom());
        user.setPrenom(updatedUser.getPrenom());
        user.setCin(updatedUser.getCin());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());
        User updated = userRepository.save(user);
        return UserMapper.mapToUserDto(updated);
    }
}
