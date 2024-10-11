package org.example.lab720202269.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/SistemaLogin").loginProcessingUrl("/submitLoginForm").successHandler(new CustomAuthenticationSuccessHandler());

        http.logout();
        http.authorizeHttpRequests()
                .requestMatchers("/cliente", "/cliente/**").hasAnyAuthority("CLIENTE")
                .requestMatchers("/gerente", "/gerente/**").hasAnyAuthority("GERENTE")
                .requestMatchers("/admin", "/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public UserDetailsManager user(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        // Incluye un tercer campo que devuelve 'true' para que Spring Security lo interprete como habilitado
        String sql1 = "select email, password, true from users where email=?";
        // Configura la consulta para obtener los roles del usuario
        String sql2 = "SELECT u.email, r.name FROM users u INNER JOIN roles r ON u.roleId = r.id WHERE u.email = ?";

        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }


}
