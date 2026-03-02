package com.swSoftware.asientos.user_ms.infrastructure.config.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(String username, List<String> roles){
        return Jwts.builder()
                .signWith(getSecret(), SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + 60000 * 300)) //  hours
                .setSubject(username)
                .compact();
    }


    public String getSubject(String token){
        return  Jwts.parserBuilder()
                .setSigningKey(getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean tokenIsValid(String token, UserDetails userDetails){
        String username = getSubject(token);
        return !tokenExpired(token) && username.equals(userDetails.getUsername());
    }

    public boolean tokenExpired(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Key getSecret(){
        byte[] keys = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keys);
    }
}