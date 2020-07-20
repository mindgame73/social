package ru.niiar.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.niiar.social.model.User;
import ru.niiar.social.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = (User) userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User " + login + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new HashSet<GrantedAuthority>());
    }
}
