package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    public final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepository") UsersRepository usersRepository,
                            @Qualifier("encoder") PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signUp(User user) {
        Optional<User> user1 = usersRepository.findByName(user.getUserName());
        if(user1.isPresent()){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }
}
