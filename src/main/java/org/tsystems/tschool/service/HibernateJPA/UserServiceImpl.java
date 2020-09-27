package org.tsystems.tschool.service.HibernateJPA;

import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.service.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);

    }

}
