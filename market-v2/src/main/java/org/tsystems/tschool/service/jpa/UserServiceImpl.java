package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.mapper.UserDtoMapper;
import org.tsystems.tschool.service.UserService;

import javax.transaction.Transactional;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserDAO userDAO;

    final PasswordEncoder passwordEncoder;

    private final UserDtoMapper mapper
            = Mappers.getMapper(UserDtoMapper.class);

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        if (user == null) return UserDto.builder().build();
        return mapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userDAO.getUserByUsername(userDto.getUsername());
        User newUser = mapper.dtoToUser(userDto);

        user.setBirthday(newUser.getBirthday());
        user.setAddress(newUser.getAddress());
        user.setLastName(newUser.getLastName());

        return mapper.userToDto(user);
    }

    @Override
    public void updatePassword(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userDAO.update(user);

    }
}
