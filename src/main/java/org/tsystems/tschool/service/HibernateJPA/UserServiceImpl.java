package org.tsystems.tschool.service.HibernateJPA;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.mapper.UserDtoMapper;
import org.tsystems.tschool.service.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserDAO userDAO;

    private final UserDtoMapper mapper
            = Mappers.getMapper(UserDtoMapper.class);

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        return user!=null?mapper.userToDto(user):null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = mapper.DtoToUser(userDto);
        return mapper.userToDto(userDAO.updateUser(user));
    }

}
