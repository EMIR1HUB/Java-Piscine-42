package edu.school21.spring.service.services;

import edu.school21.spring.service.models.User;
import edu.school21.spring.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {

    private UsersRepository repository;
    private UsersRepository repositoryTemplate;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepository") UsersRepository repository,
                            @Qualifier("usersRepositoryTemplate") UsersRepository repositoryTemplate) {
        this.repository = repository;
        this.repositoryTemplate = repositoryTemplate;
    }

    @Override
    public String signUp(String email) {
        String uuid = String.valueOf(UUID.randomUUID());
        repositoryTemplate.save(new User(email, uuid));
        return uuid.toString();
    }
}
