package org.tsystems.tschool.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tsystems.tschool.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User class data access object
 * Class to access and control user data
 */
@Repository
@Transactional
public class UserDAO {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    private static final Logger log = LogManager.getLogger(UserDAO.class);

    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT e from User e where e.username = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("Could not find User with username: " + username);
            return null;
        }
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     */
    public User update(User user) {
        entityManager.merge(user);
        return entityManager.find(User.class, user.getId());
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    public User save(User user) {
        entityManager.persist(user);
        return entityManager.find(User.class, user.getId());
    }

    public List<User> findAll() {
        return entityManager.createQuery("select e from User e", User.class)
                .getResultList();
    }
}
