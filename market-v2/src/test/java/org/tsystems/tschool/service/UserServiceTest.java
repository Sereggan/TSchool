package org.tsystems.tschool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.Address;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.service.jpa.UserServiceImpl;

import javax.persistence.NoResultException;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private User user;

    @Mock
    UserDAO userDAO;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).email("user@user.ru").username("username").build();
    }

    @DisplayName("Test getUser by valid username")
    @Test
    void getUserByUsername() {
        when(userDAO.getUserByUsername("username")).thenReturn(user);
        UserDto userDto = userService.getUserByUsername("username");
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @DisplayName("Test getUser by invalid username")
    @Test
    void getUserByInvalidUsername() {
        lenient().when(userDAO.getUserByUsername(any(String.class))).thenThrow(NoResultException.class);

        UserDto userDto = userService.getUserByUsername(any(String.class));
        assertNotNull(userDto);
    }

    @DisplayName("Test updateUser")
    @Test
    void updateUser() {
        user.setAddress(Address.builder().build());
        user.setBirthday(new Date(1L));
        UserDto userDto = UserDto.builder().id(1L).username("name").build();
        when(userDAO.getUserByUsername(userDto.getUsername())).thenReturn(user);
        UserDto updatedUser = userService.updateUser(userDto);
        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getBirthday(), updatedUser.getBirthday());
    }

    @Test
    @DisplayName("Test updatePassword")
    void updatePassword() {
        when(userDAO.getUserByUsername("name")).thenReturn(user);
        userService.updatePassword("name", "pass");
        verify(userDAO).update(any(User.class));
    }
}