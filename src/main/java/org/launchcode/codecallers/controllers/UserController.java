package org.launchcode.codecallers.controllers;

import org.launchcode.codecallers.exception.UserNotFoundException;
import org.launchcode.codecallers.models.User;
import org.launchcode.codecallers.models.data.UserRepository;
import org.launchcode.codecallers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{userID}/delete")
    public void deleteProfile(@PathVariable int userID){

        userService.deleteById(userID);

    }

    @PutMapping("/{userID}/update")
    public User updateProfile(@RequestBody User newUser, @PathVariable int userID){

        return userService.findById(userID)
                .map(user -> {
                    if (newUser.getFirstName() != null) {
                        if (!newUser.getFirstName().isBlank()) {
                            user.setFirstName(newUser.getFirstName());
                        }
                    }
                    if (newUser.getLastName() != null) {
                        if (!newUser.getLastName().isBlank()) {
                            user.setLastName(newUser.getLastName());
                        }
                    }
                    if (newUser.getBio() != null) {
                        if (!newUser.getBio().isBlank()) {
                            user.setBio(newUser.getBio());
                        }
                    }
                    if (newUser.getBirthday() != null) {
                        if (!newUser.getBirthday().isBlank()) {
                            user.setBirthday(newUser.getBirthday());
                        }
                    }
                    if (newUser.getProfilePic() != null) {
                        if (!newUser.getProfilePic().isBlank()) {
                            user.setProfilePic(newUser.getProfilePic());
                        }
                    }
                    return userService.saveUser(user);
                }).orElseThrow(() -> new UserNotFoundException(userID));

    }

    @GetMapping("/index")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}

