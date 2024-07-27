package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Services.UserService;
import com.tenstep.gestionConge.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public  UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto userdto = userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        UserDto userDto =  userService.getUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id , @RequestBody UserDto userDto){
        UserDto user = userService.updateUser(id,userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

}
