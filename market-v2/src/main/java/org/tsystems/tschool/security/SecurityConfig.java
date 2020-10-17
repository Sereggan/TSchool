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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailsService;

    private static final String ROLE_EMPLOYEE = "EMPLOYEE";
    private static final String ROLE_CLIENT = "CLIENT";

    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/categories/**").hasRole(ROLE_EMPLOYEE)
                .antMatchers("/articles/**").hasRole(ROLE_EMPLOYEE)
                .antMatchers("/user/**").hasRole(ROLE_CLIENT)
                .antMatchers("/").permitAll()
                .antMatchers("/cart").permitAll()
                .antMatchers("/cart/order").hasRole(ROLE_CLIENT)
                .antMatchers("/cart/convert").hasRole(ROLE_CLIENT)
                .antMatchers("/catalog").permitAll()
                .and().formLogin().defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/")// logout for everyone
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied")
                .and().csrf().disable();
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
