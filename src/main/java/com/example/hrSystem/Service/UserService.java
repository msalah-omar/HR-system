package com.example.hrSystem.Service;

import com.example.hrSystem.entity.User;
import com.example.hrSystem.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService

{
    private UserRepository userRepository;

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public User getByUsername(String username){
        return userRepository.loadUserByUsername(username).get();
    }
}
