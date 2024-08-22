package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.Services.UserService;
import com.tenstep.gestionConge.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public  UserController(UserService userService,UserRepository userRepository){
        this.userService=userService;
        this.userRepository=userRepository;
    }


    @GetMapping("/me")
    public User getAuthenticatedUser() {
       return userService.getConnectedUser();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        UserDto userDto =  userService.getUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }
    @GetMapping("/getU")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id , @RequestBody UserDto userDto){
        UserDto user = userService.updateUser(id,userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

}
