package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.mapper.AddressDtoMapper;
import org.tsystems.tschool.mapper.UserDtoMapper;
import org.tsystems.tschool.service.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserDtoMapper mapper
            = Mappers.getMapper(UserDtoMapper.class);

    private final AddressDtoMapper addressDtoMapper
            = Mappers.getMapper(AddressDtoMapper.class);

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        if(user==null) return null;
        return mapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User oldUser = userDAO.getUserByUsername(userDto.getUsername());
        User user = mapper.DtoToUser(userDto);
        user.setPassword(oldUser.getPassword());
        user.setRoles(oldUser.getRoles());
        user.setCart(oldUser.getCart());
        userDAO.updateUser(user);
        User updatedUser = userDAO.getUserByUsername(user.getUsername());
        return mapper.userToDto(updatedUser);
    }

    @Override
    public void updatePassword(String username, String password) {
       User user = userDAO.getUserByUsername(username);
       String encodedPassword = passwordEncoder.encode(password);
       user.setPassword(encodedPassword);
       userDAO.updateUser(user);
    }
}
