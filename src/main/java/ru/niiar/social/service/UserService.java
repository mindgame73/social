package ru.niiar.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.niiar.social.dto.UserDto;
import ru.niiar.social.exception.UserAlreadyExistsException;
import ru.niiar.social.model.User;
import ru.niiar.social.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User registerUser(UserDto userDto) throws UserAlreadyExistsException {
        if (isUserExists(userDto.getLogin())){
            throw new UserAlreadyExistsException(String.format("User %s already registered.",
                    userDto.getLogin()));
        }
        else
        {
            User user = new User();
            user.setLogin(userDto.getLogin());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            return userRepository.save(user);
        }
    }


    private boolean isUserExists(String login){
        return userRepository.findUserByLogin(login).isPresent();
    }
}
