package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    List<User> selectAll() throws SQLException;
    User findByUserName(String username) throws SQLException;
    int checkAuth(int user_id) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}
