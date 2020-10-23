package org.tsystems.tschool.dao;

//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.tsystems.tschool.TschoolApplication;
//import org.tsystems.tschool.entity.User;
//
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class UserDAOTest {
//
//    @Autowired
//    UserDAO userDAO;
//
//    @Rollback
//    @Transactional
//    @Test
//    void getUserByUsername() {
//        User user = User.builder().id(1L).email("email").username("name").password("pass").lastName("lastName").build();
//        userDAO.save(user);
//
//    }
//
//    @Test
//    void getUserById() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void save() {
//    }
//}