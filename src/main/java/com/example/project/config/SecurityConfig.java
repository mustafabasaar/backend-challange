package com.example.project.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;


@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("https://localhost:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
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
        httpSecurity.cors().configurationSource(corsConfigurationSource());
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET,"/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/products/**").hasAnyAuthority("ADMIN","STORE");
                    auth.requestMatchers(HttpMethod.PUT, "/products/**").hasAnyAuthority("ADMIN","STORE");
                    auth.requestMatchers(HttpMethod.DELETE, "/products/**").hasAnyAuthority("ADMIN","STORE");

                    auth.requestMatchers(HttpMethod.GET,"/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/categories/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/categories/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/roles/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/roles/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/roles/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/user/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/address/**").hasAnyAuthority("USER","ADMIN","STORE");
                    auth.requestMatchers(HttpMethod.POST, "/address/**").hasAnyAuthority("USER","ADMIN","STORE");
                    auth.requestMatchers(HttpMethod.PUT, "/address/**").hasAnyAuthority("USER","ADMIN","STORE");
                    auth.requestMatchers(HttpMethod.DELETE, "/address/**").hasAnyAuthority("USER","ADMIN","STORE");

                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/signup/**").permitAll();
                    auth.requestMatchers("/login/**").permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
