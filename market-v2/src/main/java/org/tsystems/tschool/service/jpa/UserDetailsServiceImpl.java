package org.tsystems.tschool.service.jpa;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.MyUserPrincipal;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.service.MyUserDetailsService;

/**
 * Created by Sergey Nikolaychuk on 23.10.2020
 */

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements MyUserDetailsService {

    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new MyUserPrincipal(user);
    }
}
