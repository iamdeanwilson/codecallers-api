package org.launchcode.codecallers.service;

import org.launchcode.codecallers.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser (User user);

    public List<User> getAllUsers();

    Optional <User> findById(int id);


}
