package com.example.marketplace.marketplace.security;


//import de.uniba.dsam.group.project.services.UserServiceImpl;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {


    private final String[] permitAllURL = {


    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .requestMatchers(this.permitAllURL).hasRole("USER")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")


                .anyRequest().authenticated()
                .and()
                .formLogin((form) -> form
                                .loginPage("/login")
                                .failureHandler(this.customAuthenticationFailureHandler())
//                        .defaultSuccessUrl("/", true)
                                .successHandler((request, response, authentication) -> {
                                    // Determine the role and redirect accordingly
                                    String username = authentication.getName();

                                    // Redirect based on username or role
                                    if ("admin".equals(username) || authentication.getAuthorities().stream()
                                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                                        response.sendRedirect("/admin/dashboard"); // Admin dashboard redirect
                                    } else {
                                        response.sendRedirect("/"); // Standard user dashboard
                                    }

                                })
                                .permitAll()

                )
                .csrf(request -> request.ignoringRequestMatchers(PathRequest.toH2Console()))
                .headers(headers -> headers.frameOptions(option -> option.sameOrigin()))
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")// Redirecting to login page after logout
                                .permitAll()
                );
        return http.build();

    }

    private AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
