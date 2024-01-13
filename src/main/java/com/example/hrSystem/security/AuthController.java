//package com.example.hrSystem.security;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/auth")
//@AllArgsConstructor
//@Tag(name = "Authentication", description = "REST API for Authentication")
//@Log4j2
//public class AuthController {
//
//    private AuthHandler authHandler;
//
//    @PostMapping(value = "/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {
//        //TODO:add encryption/decryption
//        return authHandler.login(loginRequest);
//    }
//
//    @PostMapping(value = "/logout")
//    public ResponseEntity<?> logout() {
//        return authHandler.logout();
//    }
//
//    @PostMapping(value = "/refresh")
//    public ResponseEntity<?> refreshToken(@CookieValue(name = "refreshToken", required = false) String refreshToken) {
//        return authHandler.refresh(refreshToken);
//    }
//
//    @GetMapping(value = "/user-info")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
//        return authHandler.getUserInfo(userDetails);
//    }
//
//}
