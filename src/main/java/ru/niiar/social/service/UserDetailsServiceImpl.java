package ru.niiar.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.niiar.social.model.User;
import ru.niiar.social.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new HashSet<GrantedAuthority>());
    }
}
