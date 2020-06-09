package dao.impl;

import dao.UserDAO;
import models.User;
import netscape.security.UserTarget;
import ultils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public static final String ADD_USER = "call insert_user(?,?,?,?,?,?,?)";
    public static final String ADD_USER_PERMISSION = "call insert_user_permission(?,?)";
    public static final String SELECT_ALL = "call select_all_and_permission()";
    public static final String FIND_USER = "call find_by_username(?)";
    public static final String CHECK_AUTH = "call join_check_auth(?)";
    public static final String UPDATE_USER = "call update_user(?,?,?,?,?,?,?,?)";
    public static final String DELETE_USER = "call delete_user(?)";

    private DBConnection dbConnection = DBConnection.getInstance();
    private Connection connection = dbConnection.getConnection();

    @Override
    public boolean addUser(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement addStatement = connection.prepareStatement(ADD_USER);

            addStatement.setString(1, user.getUserName());
            addStatement.setString(2, user.getPassword());
            addStatement.setString(3, user.getFirstName());
            addStatement.setString(4, user.getLastName());
            addStatement.setString(5, user.getAddress());
            addStatement.setString(6, user.getEmail());
            addStatement.setString(7, user.getPhoneNumber());
            addStatement.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<User> selectAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String username = resultSet.getString("user_name");
            String password = resultSet.getString("user_password");
            String firstName = resultSet.getString("user_firstName");
            String lastName = resultSet.getString("user_lastName");
            String address = resultSet.getString("user_address");
            String email = resultSet.getString("user_email");
            String phoneNumber = resultSet.getString("user_phoneNumber");
            String permission;
            String temp = resultSet.getString("permission_id");
            if(temp==null) temp="-1";
            int check = Integer.parseInt(temp);
           switch (check){
               case 1:
                   permission = "Admin";
                   break;
               case 2:
                   permission="Staff";
                   break;
               default:
                   permission = "Customer";
                   break;
           }

            users.add(new User(id, username, password, firstName, lastName, phoneNumber, email, address,permission));
        }
        return users;
    }

    @Override
    public User findByUserName(String username) throws SQLException {
        User user;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_USER);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String _username = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                String firstName = resultSet.getString("user_firstName");
                String lastName = resultSet.getString("user_lastName");
                String address = resultSet.getString("user_address");
                String email = resultSet.getString("user_email");
                String phoneNumber = resultSet.getString("user_phoneNumber");
                user = new User(id, _username, password, firstName, lastName, email, phoneNumber, address);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public int checkAuth(int user_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CHECK_AUTH);
        statement.setInt(1,user_id);
        int auth= -1;
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            auth = resultSet.getInt("permission_id");
        }
        return auth;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        PreparedStatement statement =  connection.prepareStatement(UPDATE_USER);
        statement.setString(1,user.getUserName());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getFirstName());
        statement.setString(4,user.getLastName());
        statement.setString(5,user.getAddress());
        statement.setString(6,user.getEmail());
        statement.setString(7,user.getPhoneNumber());
        statement.setInt(8,user.getUserId());

        statement.executeUpdate();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_USER);
        statement.setInt(1,id);
        statement.executeUpdate();
    }

}
