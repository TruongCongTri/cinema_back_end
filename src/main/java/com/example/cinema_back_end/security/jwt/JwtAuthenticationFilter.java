package com.example.cinema_back_end.security.jwt;

import com.example.cinema_back_end.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tritcse00526x
 */
//a filter
//responsible for validating the request before it reaches its destination.
//by retrieving the Authorization header and checks whether the JWT string provided by the user is valid
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
            throws ServletException, IOException {
        try {
            //get JWT from request
            String jwt = getJwtFromRequest(request);
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                //get username from JWT string
                String username = jwtService.getUserNameFromJwtToken(jwt);
                //get UserDetails information from username
                UserDetails userDetails = userService.loadUserByUsername(username);
                if(userDetails != null) { //if valid UserDetails
                    //set information for Security Context
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        //verify whether the Authorization header contains JWT information
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
