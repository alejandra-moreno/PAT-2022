package com.pat2022.pat.config;

import java.util.HashMap;
import java.util.Map;

import com.pat2022.pat.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;

    @Bean //Permanece en memoria
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder defaultEncoder = NoOpPasswordEncoder.getInstance();
        
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        //Definimos codificadores
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        //Asumir texto plano
        passwordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);

        return passwordEncoder;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                    .defaultSuccessUrl("/auth/login")
                    .and()
            .authorizeHttpRequests()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic()
            .and()
            .csrf().disable();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    
}
