package Kruchkov.Task4;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    @Test
    public void insert() throws SQLException {

        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        UserService userServiceB =context.getBean(UserService.class);
        UserService userService = userServiceB;

        User user =new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();
        for(User userD : userList)
            Assertions.assertTrue((userD.getUsername().equals("petrov") || userD.getUsername().equals("kuznezov")),
                    "Не прошла запись или чтение в/из БД");
    }

    @Test
    public void findById() throws SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        UserService userServiceB =context.getBean(UserService.class);
        UserService userService = userServiceB;

        User user =new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();

        for(User userD : userList) {
            user = userService.findById(userD.getId());
            Assertions.assertEquals(user.getUsername(), userD.getUsername());
        }
    }

    @Test
    public void findAll() throws SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        UserService userServiceB =context.getBean(UserService.class);
        UserService userService = userServiceB;

        User user =new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();

        for(User userD : userList)
            Assertions.assertTrue((userD.getUsername().equals("petrov") || userD.getUsername().equals("kuznezov")),
                    "Не прошло чтение всех записей из БД");

    }

    @Test
    public void findByUsername() throws SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        UserService userServiceB =context.getBean(UserService.class);
        UserService userService = userServiceB;

        User user =new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();
        for(User userD : userList) {
            user = userService.findByUsername(userD.getUsername());
            Assertions.assertEquals(user.getUsername(), userD.getUsername());
        }
    }

    @Test
    public void delById() throws SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        UserService userServiceB =context.getBean(UserService.class);
        UserService userService = userServiceB;

        User user =new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();
        boolean tryCatch = false;
        for(User userD : userList) {

            userService.delById(userD.getId());
            try {
                user = userService.findById(userD.getId());
            }
            catch (Exception ex) {
                tryCatch = true;
            }
            Assertions.assertTrue(tryCatch,"Не работает удаление по id");
        }

    }
    @Test
    public void delByUsername() throws SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        UserService userServiceB =context.getBean(UserService.class);
        UserService userService = userServiceB;

        User user =new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();
        boolean tryCatch = false;
        for(User userD : userList) {

            userService.delByUsername(userD.getUsername());
            try {
                user = userService.findByUsername(userD.getUsername());
            }
            catch (Exception ex) {
                tryCatch = true;
            }
            Assertions.assertTrue(tryCatch,"Не работает удаление по username");
        }
    }
}
