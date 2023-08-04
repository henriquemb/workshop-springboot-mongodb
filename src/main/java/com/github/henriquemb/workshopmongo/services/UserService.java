package com.github.henriquemb.workshopmongo.services;

import com.github.henriquemb.workshopmongo.domain.User;
import com.github.henriquemb.workshopmongo.repository.UserRepository;
import com.github.henriquemb.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ObjectNotFoundException("User not found");
        }

        return user.get();
    }

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }
}
