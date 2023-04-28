package com.nmatute.octoger.accountingmanagement.domain.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;

/**
 * Clase para Servicio de JWT tokens.
 * 
 * @author NM4TT
 */
@Service
@NoArgsConstructor
public class JwtService {

    private static final String SECRETKEY = "4528482B4B6250655368566D597133743677397A24432646294A404E63516654";
    private final int EXPIRATION_TIME = 1000 * 60 * 24; //24h + 1 seg
    private final Logger log = LoggerFactory.getLogger(JwtService.class);

    public String extractUsername(String jwt) {
        String username = extractClaim(jwt, Claims::getSubject);
        log.debug("Username extracted = ".concat(username));
        return username;
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map <String, Object> extraClaims, 
                                UserDetails userDetails){

        return Jwts
               .builder()
               .setClaims(extraClaims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
               .signWith(getSignInKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails){
        boolean isValid = extractUsername(jwt).equals(userDetails.getUsername()) && !isTokenExpired(jwt);
        log.debug(
            (isValid) ? "Token received is valid." : "Token received is not valid."
        );
        return extractUsername(jwt).equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

}
