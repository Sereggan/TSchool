package org.tsystems.tschool.service.HibernateJPA;

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
        UserDto userDto = mapper.userToDto(user);
        userDto.setAddressDto(addressDtoMapper.addressToDto(user.getAddress()));
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User oldUser = userDAO.getUserById(userDto.getId());
        User user = mapper.DtoToUser(userDto);
        user.setPassword(oldUser.getPassword());
        user.setRoles(oldUser.getRoles());
        user.setCart(oldUser.getCart());
        user.setAddress(addressDtoMapper.DtoToAddress(userDto.getAddressDto()));
        userDAO.updateUser(user);
        User updatedUser = userDAO.getUserByUsername(user.getUsername());
        UserDto updatedUserDto = mapper.userToDto(updatedUser);
        updatedUserDto.setAddressDto(addressDtoMapper.addressToDto(updatedUser.getAddress()));
        return updatedUserDto;
    }
}
