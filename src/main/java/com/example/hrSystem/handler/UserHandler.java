package com.example.hrSystem.handler;

import com.example.hrSystem.Security.TokenProvider;
import com.example.hrSystem.Security.TokenResponse;
import com.example.hrSystem.Service.UserService;
import com.example.hrSystem.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@AllArgsConstructor
@Component
public class UserHandler
{
    private final PasswordEncoder passwordEncoder;

    private UserService userService;

    private TokenProvider tokenProvider;

    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> save() {
        User user = new User();
        user.setUsername("mosalah");
        user.setEmail("mohamed@salah.com");
        user.setPhone("015336522525");
        user.setPassword(passwordEncoder.encode("123"));
        userService.save(user);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<?> login(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password, new ArrayList<>()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User byUsername = userService.getByUsername(username);
        String token = tokenProvider.generateToken(byUsername.getUsername());

        return ResponseEntity.ok(new TokenResponse(token));
    }

}
