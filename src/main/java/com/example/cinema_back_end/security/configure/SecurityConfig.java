package com.example.cinema_back_end.security.configure;

import com.example.cinema_back_end.security.jwt.JwtAuthenticationFilter;
import com.example.cinema_back_end.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author tritcse00526x
 */
@Configuration
@EnableWebSecurity //activate spring security on web app
public class SecurityConfig extends WebSecurityConfigurerAdapter { //a class dedicated to the processing of information pertinent to security matters.
    @Autowired
    private IUserService userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager(); //get AuthenticationManager bean
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10); //password encoder, utilized by Spring Security for encoding user passwords
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) //provide a userService for Spring Security
                .passwordEncoder(passwordEncoder()); //provide password encoder
    }

    // access control
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/reviews").hasRole("CLIENT")
                .antMatchers("/**", "/login","/register",
                        "/api/forgot-password**","/api/forgot-password/validate-token",
                        "/api/movies/**","/api/branches/**", "/api/cities/**",
                        "/api/promotions/active/**", "/api/posts/**",
                        "/api/schedule/active/all-schedule-dates", "/api/schedule/active/**").permitAll() //permit unrestricted access to these addresses for all individuals
                .antMatchers("/api/admin/**").hasRole("ADMIN") // permit restricted access to these addresses for only individuals has admin authorization
                .antMatchers(HttpMethod.POST,"/api/reviews").hasRole("CLIENT")
                .antMatchers("/api/**").hasRole("CLIENT") // permit restricted access to these addresses for only individuals has client authorization
                .anyRequest().authenticated() // all other requests must be authenticated prior to access
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) //add another filter layer to validate the JWT
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors(); //prevent requests from an external domain
    }
}
