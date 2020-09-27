package org.tsystems.tschool.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT e from User e where e.username = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User updateUser(User user){
        User existingUser = entityManager.find(User.class, user.getId());
        user.setId(existingUser.getId());
        entityManager.merge(user);
        return entityManager.find(User.class, user);
    }
}
