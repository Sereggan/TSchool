package org.tsystems.tschool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tsystems.tschool.service.MyUserDetailsService;
import org.tsystems.tschool.service.jpa.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailsService;

    private static final String ROLE_EMPLOYEE = "EMPLOYEE";
    private static final String ROLE_CLIENT = "CLIENT";
    private static final String ROLE_ADMIN = "ADMIN";

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/categories/**").hasRole(ROLE_EMPLOYEE)
                .antMatchers("/articles/**").hasRole(ROLE_EMPLOYEE)
                .antMatchers("/orders").hasRole(ROLE_EMPLOYEE)
                .antMatchers("/user/**").hasRole(ROLE_CLIENT)
                .antMatchers("/cart").permitAll()
                .antMatchers("/cart/convert").hasRole(ROLE_CLIENT)
                .antMatchers("/admin/**").hasRole(ROLE_ADMIN)
                .antMatchers("/catalog").permitAll()
                .and().formLogin().defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/");// logout for everyone

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
