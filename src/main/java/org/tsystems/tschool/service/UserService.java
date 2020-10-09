package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.UserDto;

public interface UserService {

    public UserDto getUserByUsername(String username);

    public UserDto updateUser(UserDto userDto);

    public void updatePassword(String username, String password);


}
