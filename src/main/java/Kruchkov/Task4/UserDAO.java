package Kruchkov.Task4;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DataSource dataSource;
    private Connection  con;
    private String      tableName;
    private Statement statement ;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.tableName = "student.user";
        try {
            this.con = this.dataSource.getConnection();
            statement = con.createStatement();
        }
        catch(SQLException ex) {
            throw new RuntimeException((ex));
        }
    }
    public void insert(User user) throws SQLException {
        String query = "INSERT INTO "+this.tableName + " (username) VALUES ("+'\''+user.getUsername()+'\''+")";
        System.out.println(" query = "+query);
        try
        {
            statement.execute(query);
        }
        catch(SQLException e){ throw e; }
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id AS id, username AS username FROM "+this.tableName;
        try
        {
            ResultSet res =  statement.executeQuery(query);
            while (true) {
                res.next();
                long id = res.getLong("id");
                String username = res.getString("username");
                users.add(new User(id, username));
                if (res.isLast()) break;
            }
            return users;
        }
        catch(SQLException e){ throw e; }
    }

    public User findById(long id) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id AS id, username AS username FROM "+this.tableName +
                " WHERE id = "+String.valueOf(id);
        try
        {
            ResultSet res =  statement.executeQuery(query);
            res.next();
            id = res.getLong("id");
            String username = res.getString("username");
            return new User(id, username);
        }
        catch(SQLException e){ throw e; }
    }

    public User findByUsername(String username) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id AS id, username AS username FROM "+this.tableName +
                " WHERE username = "+'\''+username+'\'';
        try
        {
            ResultSet res =  statement.executeQuery(query);

            res.next();
            long id = res.getLong("id");
            username = res.getString("username");
            return new User(id, username);
        }
        catch(SQLException e){ throw e; }
    }

    public void delById(long id) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "DELETE FROM "+this.tableName +
                " WHERE id = " + String.valueOf(id);
        try
        {
            statement.execute(query);
        }
        catch(SQLException e){ throw e; }
    }

    public void delByUsername(String username) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "DELETE FROM "+this.tableName +
                " WHERE username=" + '\'' + username + '\'';
        try
        {
            System.out.println(query);
            statement.execute(query);
        }
        catch(SQLException e){ throw e; }
    }

}
