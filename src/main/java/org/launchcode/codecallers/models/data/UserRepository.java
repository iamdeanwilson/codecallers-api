package org.launchcode.codecallers.models.data;

import org.launchcode.codecallers.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User findByEmail(String email);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
