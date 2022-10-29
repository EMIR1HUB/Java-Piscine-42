package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsersRepository extends CrudRepository {
    Optional<User> findByEmail(String email);
}
