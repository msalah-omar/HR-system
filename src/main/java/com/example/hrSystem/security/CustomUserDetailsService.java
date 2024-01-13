//package com.example.hrSystem.security;
//
//import com.example.hrSystem.entity.User;
//import com.example.hrSystem.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//@Log4j2
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("load user by username");
//        User user = userRepository.findByUsernameWithRoles(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//        if (user.getRoles().isEmpty()) {
//            throw new AccessDeniedException("User Doesn't have any Roles.");
//        }
//
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getCode()))
//                .collect(Collectors.toList());
//        return new HrUser(username, user.getPassword(), user.getId(),
//                user.getIsActive(), true, true, true, authorities);
//    }
//}
