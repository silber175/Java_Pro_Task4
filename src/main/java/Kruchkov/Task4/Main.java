package Kruchkov.Task4;

import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;
import java.util.List;

@ComponentScan
public class Main {
    public static void main(String[] args)  throws  SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Main.class);
        UserService userService =context.getBean(UserService.class);

        User user = new User("petrov");
        userService.insert(user);

        user =new User("kuznezov");
        userService.insert(user);

        List<User> userList = userService.findAll();
        for(User userD : userList)
            Assertions.assertTrue((userD.getUsername().equals("petrov") || userD.getUsername().equals("kuznezov")),
                    "Не прошла запись или чтение в/из БД");

         userList = userService.findAll();
        for(User userD : userList) {
            user = userService.findById(userD.getId());
            Assertions.assertEquals(user.getUsername(), userD.getUsername());
        }

        userList = userService.findAll();
        for(User userD : userList)
            Assertions.assertTrue((userD.getUsername().equals("petrov") || userD.getUsername().equals("kuznezov")),
                    "Не прошло чтение всех записей из БД");

        userList = userService.findAll();
        for(User userD : userList) {
            user = userService.findByUsername(userD.getUsername());
            Assertions.assertEquals(user.getUsername(), userD.getUsername());
        }

        userList = userService.findAll();
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
}

