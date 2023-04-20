package com.nmatute.octoger.usermanagement.domain.service;

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
 * Servicio de JWT para manipular token.
 * 
 * @author NM4TT
 */
@Service
@NoArgsConstructor
public class JwtService {

    private static final String SECRETKEY = "4528482B4B6250655368566D597133743677397A24432646294A404E63516654";
    private final int EXPIRATION_TIME = 1000 * 60 * 24; //24h + 1 seg
    private final Logger log = LoggerFactory.getLogger(JwtService.class);

    /**
     * Metodo para extraer username de JWT Token.
     * @param jwt token
     * @return username de usuario
     */
    public String extractUsername(String jwt) {
        String username = extractClaim(jwt, Claims::getSubject);
        log.debug("Username extracted = ".concat(username));
        return username;
    }

    /**
     * Metodo para extraer un Claim del JWT token.
     * @param <T> Claim de clase T
     * @param jwt token
     * @param claimResolver
     * @return Claims
     */
    public <T> T extractClaim(String jwt, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    /**
     * Metodo para extraer todos los Claims de un JWT token.
     * @param jwt token
     * @return Claims
     */
    private Claims extractAllClaims(String jwt){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
    }

    /**
     * Metodo para definir el SignInKey del JWT token.
     * @return SignInKey
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Metodo para generar JWT token.
     * @param extraClaims claims
     * @param userDetails detalles de usuario
     * @return JWT token
     */
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

    /**
     * Metodo para generar JWT token con solo detalles de usuario.
     * @param userDetails detalles de usuario
     * @return JWT Token
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Metodo para verificar si JWT Token es valido.
     * @param jwt token
     * @param userDetails detalles de usuario
     * @return cierto/falso
     */
    public boolean isTokenValid(String jwt, UserDetails userDetails){
        boolean isValid = extractUsername(jwt).equals(userDetails.getUsername()) && !isTokenExpired(jwt);
        log.debug(
            (isValid) ? "Token received is valid." : "Token received is not valid."
        );
        return extractUsername(jwt).equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    /**
     * Metodo para verificar si token expiro.
     * @param jwt token
     * @return cierto/falso
     */
    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    /**
     * Metodo para extraer fecha de expiracion de JWT token.
     * @param jwt token
     * @return Fecha de expiracion
     */
    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

}
