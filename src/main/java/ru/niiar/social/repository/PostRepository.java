package ru.niiar.social.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niiar.social.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
