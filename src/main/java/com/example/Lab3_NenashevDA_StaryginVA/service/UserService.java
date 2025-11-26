package com.example.Lab3_NenashevDA_StaryginVA.service;

import com.example.Lab3_NenashevDA_StaryginVA.model.User;
import com.example.Lab3_NenashevDA_StaryginVA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User updated) {
        return userRepository.findById(id).map(user -> {
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            user.setAge(updated.getAge());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}