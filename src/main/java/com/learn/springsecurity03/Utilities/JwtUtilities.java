package com.learn.springsecurity03.Utilities;


import com.learn.springsecurity03.Model.Security.ISecurityUserRepository;
import com.learn.springsecurity03.Model.Security.SecurityUser;
import com.learn.springsecurity03.Service.ISecurityUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtilities {

    @Autowired
    public ISecurityUserService suService;

    public static final long serialVersionUID= 12345678L;

    public static final long HOURS = (5 * 60 * 60);

    public final String secretKey = "SPRING123";

    public String generateToken(UserDetails su){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(su.getUsername())
                .setIssuedAt(new Date((System.currentTimeMillis())))
                .setExpiration(new Date(System.currentTimeMillis()+HOURS *1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public UserDetails validateToken(String token){

        return suService.loadUserByUsername(getUserNameFromToken(token));
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }



}
