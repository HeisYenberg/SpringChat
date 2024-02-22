package com.heisyenberg.springchatserver.services;

import com.heisyenberg.springchatserver.models.User;
import com.heisyenberg.springchatserver.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UsersRepository repository) {
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public User signIn(String username, String password) {
        Optional<User> user = repository.findUserByUsername(username);
        if (user.isPresent()) {
            String encodedPassword = user.get().getPassword();
            if (encoder.matches(password, encodedPassword)) {
                return user.get();
            }
        }
        return null;
    }

    public User signUp(String username, String password) {
        Optional<User> user = repository.findUserByUsername(username);
        if (!user.isPresent()) {
            User newUser = new User(null, username, encoder.encode(password));
            return repository.save(newUser);
        }
        return null;
    }

    public User getUserById(Long id) {
        return repository.getReferenceById(id);
    }
}
