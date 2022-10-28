package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    void save(User entity);
    void update(User entity);
    void delete(Long id);
}
