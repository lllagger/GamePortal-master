package com.GamePortal.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.authorizeRequests()
                    .antMatchers("/add/game").hasRole("USER")
                    .antMatchers("/add/game").hasRole("ADMIN")
                    .antMatchers("/update/game").hasRole("USER")
                    .antMatchers("/update/game").hasRole("ADMIN")
                    .antMatchers("/delete/game/{id}").hasRole("USER")
                    .antMatchers("/delete/game/{id}").hasRole("ADMIN")
                    .antMatchers("/get/game/{id}").hasRole("USER")
                    .antMatchers("/get/game/{id}").hasRole("ADMIN")
                    .antMatchers("/games/export/excel").hasRole("USER")
                    .antMatchers("/games/export/excel").hasRole("ADMIN")
                    .antMatchers("/games/import/excel").hasRole("USER")
                    .antMatchers("/games/import/excel").hasRole("ADMIN")
                    .antMatchers("/buy/{userId}/{gameId}").hasRole("USER")
                    .antMatchers("/buy/{userId}/{gameId}").hasRole("ADMIN")
                    .antMatchers("/add/user").hasRole("ADMIN")
                    .antMatchers("/add/user").hasRole("USER")
                    .antMatchers("/update/user").hasRole("ADMIN")
                    .antMatchers("/update/user").hasRole("USER")
                    .antMatchers("/delete/user/{id}").hasRole("ADMIN")
                    .antMatchers("/delete/user/{id}").hasRole("USER")
                    .antMatchers("/get/user/{id}").hasRole("USER")
                    .antMatchers("/get/user/{id}").hasRole("ADMIN")
                    .antMatchers("/users/export/excel").hasRole("USER")
                    .antMatchers("/users/export/excel").hasRole("ADMIN")
                    .antMatchers("user/import/excel").hasRole("USER")
                    .antMatchers("user/import/excel").hasRole("ADMIN")
                    .antMatchers("/").permitAll()
                    .and().httpBasic()
                    .and().formLogin().permitAll()
                    .and().csrf().disable();
        } catch (Exception e) {
            log.error("Check user roles.");
        }
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }
}
