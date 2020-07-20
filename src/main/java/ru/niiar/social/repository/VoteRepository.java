package ru.niiar.social.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niiar.social.model.Vote;

public interface VoteRepository extends CrudRepository<Vote,Integer> {
}
