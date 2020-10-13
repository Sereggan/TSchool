package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tsystems.tschool.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT e from User e where e.username = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User update(User user) {
        entityManager.merge(user);
        return entityManager.find(User.class, user.getId());
    }

    public User save(User user) {
        entityManager.persist(user);
        return entityManager.find(User.class, user.getId());
    }
}
