package org.tsystems.tschool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.service.jpa.UserDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername() {
        when(userDAO.getUserByUsername("name")).thenReturn(User.builder().username("name").build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("name");
        assertEquals("name", userDetails.getUsername());
    }

    @Test
    void loadUserByWrongUsername() {
        when(userDAO.getUserByUsername("name")).thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("name");
        });
    }
}