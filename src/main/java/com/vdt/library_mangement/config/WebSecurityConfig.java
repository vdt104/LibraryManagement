package com.vdt.library_mangement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vdt.library_mangement.entity.Role;
import com.vdt.library_mangement.filters.JwtTokenFilter;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Value("${api.prefix}")
    private String apiPrefix;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(POST,
                                    String.format("/%s/readers**", apiPrefix),
                                    String.format("/%s/users/login", apiPrefix)
                            )
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("/%s/document_copies/**", apiPrefix),
                                    String.format("/%s/documents/**", apiPrefix)
                            )
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("/%s/readers/{id}", apiPrefix)).hasAnyRole(Role.READER, Role.LIBRARIAN)
                        
                            .requestMatchers(GET,
                                    String.format("/%s/readers", apiPrefix)).hasRole(Role.LIBRARIAN)

                            .requestMatchers(PUT,
                                    String.format("/%s/readers/{id}", apiPrefix)).hasAnyRole(Role.READER, Role.LIBRARIAN)

                            .requestMatchers(PUT,
                                    String.format("/%s/readers/{id}/is_active", apiPrefix)).hasRole(Role.LIBRARIAN)

                            .requestMatchers(POST,
                                    String.format("/%s/readers/{id}/bookshelf", apiPrefix)).hasRole(Role.LIBRARIAN)

                            .requestMatchers(POST,
                                    String.format("/%s/documents/**", apiPrefix)).hasRole(Role.LIBRARIAN)

                            .requestMatchers(POST,
                                    String.format("/%s/document_copies/**", apiPrefix)).hasRole(Role.LIBRARIAN)

                            .requestMatchers(PUT,
                                    String.format("/%s/document_copies/**", apiPrefix)).hasRole(Role.LIBRARIAN)

                            .requestMatchers(POST,
                                    String.format("/%s/reader_requests", apiPrefix)).hasAnyRole(Role.READER, Role.LIBRARIAN)

                            .requestMatchers(GET,
                                    String.format("/%s/reader_requests/**", apiPrefix)).hasAnyRole(Role.READER, Role.LIBRARIAN)

                            .requestMatchers(PUT,
                                    String.format("/%s/reader_requests/{id}/status", apiPrefix)).hasRole(Role.LIBRARIAN)
                        
                            .requestMatchers(PUT,
                                    String.format("/%s/reader_requests/{id}/action=cancel", apiPrefix)).hasRole(Role.READER)

                            .requestMatchers(POST,
                                    String.format("/%s/librarians", apiPrefix)).hasRole(Role.ADMIN)

                            .anyRequest().authenticated();
                        })
                        .csrf(AbstractHttpConfigurer::disable);
                http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                        configuration.setExposedHeaders(List.of("x-auth-token"));
                        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                        source.registerCorsConfiguration("/**", configuration);
                        httpSecurityCorsConfigurer.configurationSource(source);
                    }
                });
        
        return http.build();
    }
}   
