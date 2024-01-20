package com.example.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET,"/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/products/**").hasAnyRole("admin","store");
                    auth.requestMatchers(HttpMethod.PUT, "/products/**").hasAnyRole("admin","store");
                    auth.requestMatchers(HttpMethod.DELETE, "/products/**").hasAnyRole("admin","store");

                    auth.requestMatchers(HttpMethod.GET,"/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/categories/**").hasRole("admin");
                    auth.requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("admin");
                    auth.requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("admin");

                    auth.requestMatchers(HttpMethod.GET,"/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/roles/**").hasRole("admin");
                    auth.requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("admin");
                    auth.requestMatchers(HttpMethod.DELETE, "/roles/**").hasRole("admin");

                    auth.requestMatchers(HttpMethod.GET,"/user/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/user/**").hasRole("admin");
                    auth.requestMatchers(HttpMethod.PUT, "/user/**").hasRole("admin");
                    auth.requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("admin");

                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/signup/**").permitAll();
                    auth.requestMatchers("/login/**").permitAll();
                })
               // .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
              //  .oauth2Login(Customizer.withDefaults())
                .build();
    }

}
