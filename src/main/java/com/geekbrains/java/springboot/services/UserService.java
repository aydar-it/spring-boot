package com.geekbrains.java.springboot.services;

import com.geekbrains.java.springboot.models.User;
import com.geekbrains.java.springboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User find(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No results for id " + id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUserWithMaxAgeFiltering(Integer maxAge) {
        List<User> list = this.getAll();
        if (maxAge == null) {
            return list;
        }
        list.removeIf(e -> e.getAge() > maxAge);
        return list;
    }

    public long getUserCount() {
        return userRepository.count();
    }
}
