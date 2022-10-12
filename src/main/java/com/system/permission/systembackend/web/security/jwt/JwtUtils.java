package com.system.permission.systembackend.web.security.jwt;



import com.system.permission.systembackend.services.impl.UserDetailsImpl;
import com.system.permission.systembackend.web.exceptions.AppException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${api.app.jwtSecret}")
    private String jwtSecret;

    @Value("${api.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new AppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
        } catch (MalformedJwtException e) {
            throw new AppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
        } catch (ExpiredJwtException e) {
            throw new AppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
        } catch (UnsupportedJwtException e) {
            throw new AppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
        } catch (IllegalArgumentException e) {
            throw new AppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
        }
    }
}