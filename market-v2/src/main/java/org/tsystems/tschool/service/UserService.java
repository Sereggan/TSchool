package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.dto.UserItemDto;

import java.util.List;

/**
 * The interface User to access and control User business logic.
 */
public interface UserService {

    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    public UserDto getUserByUsername(String username);

    /**
     * Update user transfer object.
     *
     * @param userDto the user transfer object
     * @return the user transfer object
     */
    public UserDto updateUser(UserDto userDto);

    /**
     * Update password.
     *
     * @param username the username
     * @param password the password
     */
    public void updatePassword(String username, String password);

    /**
     * Find all users.
     *
     * @return the list of all Users
     */
    public List<UserItemDto> findAll();
}
