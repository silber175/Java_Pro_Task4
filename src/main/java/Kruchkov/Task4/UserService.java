package Kruchkov.Task4;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    public void insert(User user) throws SQLException {
        this.userDAO.insert(user);
    }

    public List<User> findAll() throws SQLException {
        return this.userDAO.findAll();
    }

    public User findById(long id) throws SQLException {
        return this.userDAO.findById(id);
    }

    public User findByUsername(String username) throws SQLException {
        return this.userDAO.findByUsername(username);
    }

    public void delById(long id) throws SQLException {
        this.userDAO.delById(id);
    }

    public void delByUsername(String username) throws SQLException {
        this.userDAO.delByUsername(username);
    }
}
