package org.launchcode.codecallers.controllers;

import org.launchcode.codecallers.models.User;
import org.launchcode.codecallers.models.data.UserRepository;
import org.launchcode.codecallers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String create(@RequestBody User user){
        userService.saveUser(user);
        return "New User Added!";
    }


    @GetMapping("/index")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}

