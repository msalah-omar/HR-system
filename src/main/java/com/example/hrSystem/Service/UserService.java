package com.example.hrSystem.Service;

import com.example.hrSystem.entity.User;
import com.example.hrSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public Page<User> getAll(String username, String arabicName, String roleCode, Integer page, Integer size) {
        return userRepository.findAllWithRoles(username, arabicName, roleCode, PageRequest.of(page, size));
    }

    public User getReferenceById(Integer id) {
        return userRepository.getById(id);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByIdWithRoles(Integer id) {
        return userRepository.findByIdWithRoles(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
