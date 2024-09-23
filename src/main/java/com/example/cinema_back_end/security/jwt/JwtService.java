package com.example.cinema_back_end.security.jwt;

import com.example.cinema_back_end.security.UserPrinciple;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tritcse00526x
 */
// Json web Token
// an encoded string sent in the header of the client request,
// serving to enable the server to verify the validity of the user's request
@Component
@Service
public class JwtService { //a class dedicated to encode UserDetails to JWT string\

    private static final String SECRET_KEY = "funixse00526x"; //the JWT_SECRET string is confidential and known only to the server
    private static final long EXPIRE_TIME = 86400000000L; //the validity period of the JWT string
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());

    //generate a JWT string from UserDetails information
    public String generateTokenLogin(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        //create a JWT string from username
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    //extract UserDetails information from the JWT
    public String getUserNameFromJwtToken(String token) {

        String userName = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }


}
