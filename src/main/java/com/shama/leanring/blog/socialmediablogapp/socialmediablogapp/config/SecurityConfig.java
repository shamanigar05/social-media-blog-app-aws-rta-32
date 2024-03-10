package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.config;


import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.security.JwtAuthenticationEntryPoint;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                .csrf( csrf -> csrf.disable())
                        .authorizeHttpRequests(authorize -> authorize
                       // .requestMatchers(HttpMethod.GET, "/api/**").permitAll())
                         .requestMatchers("/api/auth/**").permitAll())
                       .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                        .exceptionHandling(exepection -> exepection.authenticationEntryPoint(jwtAuthenticationEntryPoint));
                      //  .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails bharath = User.builder()
                .username("shama")
               .password(passwordEncoder().encode("shama123"))
                //.password("R3VydTA0MjE=")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(bharath, admin);

    }

*/

}
