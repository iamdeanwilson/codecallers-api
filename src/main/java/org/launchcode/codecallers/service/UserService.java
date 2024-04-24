package org.launchcode.codecallers.service;

import org.launchcode.codecallers.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public ResponseEntity<?> saveUser (User user);

    public ResponseEntity<?> confirmEmail(String confirmationToken);
    public List<User> getAllUsers();

    Optional <User> findById(int id);

    public void deleteById(int id);

}
