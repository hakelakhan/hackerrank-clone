package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.UsersRepository;
import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import com.lakhan.restprojects.hackerrankclone.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        log.info("Authenticating user with email " + username);
        Optional<User> userOptional = usersDao.findByEmail(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user found with email " + username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRegistrationStatus().equals(RegistrationStatus.ACTIVATED), true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
