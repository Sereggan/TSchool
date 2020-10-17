package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.UserDto;

/**
 * The interface User service.
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
     * Update user user transfer object.
     *
     * @param userDto the user dto
     * @return the user dto
     */
    public UserDto updateUser(UserDto userDto);

    /**
     * Update password.
     *
     * @param username the username
     * @param password the password
     */
    public void updatePassword(String username, String password);
}
