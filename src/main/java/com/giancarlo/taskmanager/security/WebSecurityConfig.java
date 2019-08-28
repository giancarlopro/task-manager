package com.giancarlo.taskmanager.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * WebSecurity
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/api/**")
            .hasAnyAuthority("ADMIN", "RESTW")
            .antMatchers(HttpMethod.DELETE, "/api/**")
            .hasAnyAuthority("ADMIN", "RESTW")
            .antMatchers("/api/**")
            .hasAnyAuthority("ADMIN", "RESTR", "RESTW")
            .antMatchers(HttpMethod.POST, "/users")
            .hasAnyAuthority("ADMIN", "VIEWw")
            .antMatchers(HttpMethod.POST, "/tasks")
            .hasAnyAuthority("ADMIN", "VIEWw")
            .antMatchers("/tasks/*/done")
            .hasAnyAuthority("ADMIN", "VIEWW")
            .antMatchers("/tasks/**", "/users/**")
            .hasAnyAuthority("ADMIN", "VIEWR", "VIEWW")
            // .antMatchers("/api")
            // .hasAnyAuthority("ADMIN", "RESTW", "RESTR")
            // .antMatchers("/tasks")
            // .hasAnyAuthority("ADMIN", "VIEWW", "VIEWR")
            // .antMatchers(HttpMethod.GET, "/users")
            // .hasAnyAuthority("ADMIN", "VIEWW", "VIEWR")
            .and()
            .formLogin()
            .loginPage("/")
            .loginProcessingUrl("/")
            .defaultSuccessUrl("/tasks")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(new BCryptPasswordEncoder())
            .usersByUsernameQuery("select email as username, password, enabled from user where email = ?")
            .authoritiesByUsernameQuery("select email as username, authority from user where email = ?");
    }
}