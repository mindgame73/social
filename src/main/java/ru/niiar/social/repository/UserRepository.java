package ru.niiar.social.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niiar.social.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<Object> findUserByLogin(String login);
}
