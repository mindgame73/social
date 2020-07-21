package ru.niiar.social.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niiar.social.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    List<Post> findAllByOrderByPostTimeDesc();
}
