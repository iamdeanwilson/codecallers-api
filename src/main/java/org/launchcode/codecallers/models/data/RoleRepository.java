package org.launchcode.codecallers.models.data;

import org.launchcode.codecallers.models.ERole;
import org.launchcode.codecallers.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
