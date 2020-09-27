package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.UserDto;

public interface UserService {

    public UserDto getUserByUsername(String username);
}
