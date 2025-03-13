package br.com.crisgo.iservice.config;

import br.com.crisgo.iservice.infra.SecurityFilter;
import br.com.crisgo.iservice.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthorizationService authorizationService;
    @Autowired
    private SecurityFilter securityFilter;
    @Autowired
    public SecurityConfig(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        //AUTH
                        .requestMatchers(HttpMethod.GET, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        //USERS
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyRole("ADMIN", "COMMON_USER")
                        .requestMatchers(HttpMethod.PUT, "api/v1/user/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/user/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")

                        //REVIEWS
                        .requestMatchers(HttpMethod.POST, "api/v1/reviews/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/reviews/**").hasAnyRole("ADMIN", "COMMON_USER")
                        .requestMatchers(HttpMethod.GET, "api/v1/reviews/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")

                        //SELLER
                        .requestMatchers(HttpMethod.POST, "api/v1/seller/**").hasAnyRole("ADMIN", "COMMON_USER")
                        .requestMatchers(HttpMethod.GET, "api/v1/seller/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/seller/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")

                        //ORDERS
                        .requestMatchers(HttpMethod.POST, "api/v1/orders/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")
                        .requestMatchers(HttpMethod.GET, "api/v1/seller/**").hasAnyRole("ADMIN","COMMON_USER", "SELLER")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/seller/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")

                        //ADDRESS
                        .requestMatchers(HttpMethod.GET, "api/v1/address/**").hasAnyRole("ADMIN", "COMMON_USER", "SELLER")

                        //PRODUCT
                        .requestMatchers(HttpMethod.DELETE, "api/v1/product/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/product/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers(HttpMethod.PUT, "api/v1/product/**").hasAnyRole("ADMIN","SELLER")
                        .requestMatchers(HttpMethod.GET, "api/v1/product/**").hasAnyRole("ADMIN","SELLER")

                        // swagger
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()

                        .anyRequest().hasAnyRole("ADMIN", "COMMON_USER", "SELLER")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(authorizationService)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



