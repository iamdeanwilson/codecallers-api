package org.launchcode.codecallers.security.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException;
}

