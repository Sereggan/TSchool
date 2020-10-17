package org.tsystems.tschool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.tsystems.tschool.service.jpa.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final DataSource dataSource;

    private static final String ROLE_EMPLOYEE = "EMPLOYEE";
    private static final String ROLE_CLIENT = "CLIENT";

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder()); //Sergey : pass
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
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
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }
}
