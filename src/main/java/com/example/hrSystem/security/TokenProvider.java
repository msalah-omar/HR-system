//package com.example.hrSystem.security;
//
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.SignatureException;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//@Component
//
//public class TokenProvider {
//
//   // @Value("${app.jwt.token.expiration-in-ms}")
//    private Long tokenExpirationMillis;
//
//    //@Value("${app.jwt.refresh.expiration-in-ms}")
//    private Long refreshTokenExpirationMillis;
//
////    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//   // @Value("${app.jwt.secret}")
//    private  String secretKey;
//
//    public String generateAccessToken(String subject) {
//        Date now = new Date();
//        Long duration = now.getTime() + tokenExpirationMillis;
//        Date expiryDate = new Date(duration);
//
//        String token = Jwts.builder()
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//        return token;
//    }
//
//    public String generateRefreshToken(String subject) {
//        Date now = new Date();
//        Long duration = now.getTime() + refreshTokenExpirationMillis;
//        Date expiryDate = new Date(duration);
//        String token = Jwts.builder()
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//        return token;
//    }
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//
//    public LocalDateTime getExpiryDateFromToken(String token) {
//        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
//        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            if (!StringUtils.hasText(token))
//                return false;
//            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            ex.printStackTrace();
//        } catch (MalformedJwtException ex) {
//            ex.printStackTrace();
//        } catch (ExpiredJwtException ex) {
//            ex.printStackTrace();
//        } catch (UnsupportedJwtException ex) {
//            ex.printStackTrace();
//        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//}
