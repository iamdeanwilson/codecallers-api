package org.launchcode.codecallers.controllers;

import jakarta.validation.Valid;
import org.launchcode.codecallers.exception.UserNotFoundException;
import org.launchcode.codecallers.models.ERole;
import org.launchcode.codecallers.models.Role;
import org.launchcode.codecallers.models.User;
import org.launchcode.codecallers.models.data.RoleRepository;
import org.launchcode.codecallers.models.data.UserRepository;
import org.launchcode.codecallers.payload.LoginRequest;
import org.launchcode.codecallers.payload.MessageResponse;
import org.launchcode.codecallers.payload.SignupRequest;
import org.launchcode.codecallers.payload.UserInfoResponse;
import org.launchcode.codecallers.security.jwt.JwtUtils;
import org.launchcode.codecallers.security.services.UserDetailsImpl;
import org.launchcode.codecallers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getScore(),
                signUpRequest.getBirthday(),
                signUpRequest.getBio(),
                signUpRequest.getProfilePic());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserInfoResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @DeleteMapping("/{userID}/delete")
    public void deleteProfile(@PathVariable int userID){

        userService.deleteById(userID);

    }

    @PutMapping("/{userID}/update")
    public User updateProfile(@RequestBody User newUser, @PathVariable int userID){

        User scoreUser = userService.findById(userID).get();

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
                    if (newUser.getScore() > 0 ) {
                        user.setScore(scoreUser.getScore() + newUser.getScore());
                    }
                    return userService.saveUser(user);
                }).orElseThrow(() -> new UserNotFoundException(userID));

    }

    @GetMapping("/index")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}

