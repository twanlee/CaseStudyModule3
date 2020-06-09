package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;

@WebServlet(name = "CommandUserServlet", urlPatterns = "/command")
public class CommandUserServlet extends HttpServlet {
    UserDAO userDAO = new UserDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
            String command = request.getParameter("action");
            if(command==null) command = "";
            try {
                switch (command){
                    case "edit":
                        updateUser(request,response);
                        break;
                    case "add":
                        break;
                    case "appoint":
                        break;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }


        response.setContentType("text/html;character=utf-8");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("action");
        if(command==null) command = "";
        try {
            switch (command){
                case "edit":
                    showEditForm(request,response);
                    break;
                case "delete":
                    deleteUser(request,response);
                    break;
                case "add":
                    break;
                case "appoint":
                    break;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_management");
        dispatcher.forward(request,response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String username = request.getParameter("user");
        User user = userDAO.findByUserName(username);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/user_view/edit_form.jsp");
        dispatcher.forward(request,response);
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        User user = new User(id,username,password,firstName,lastName,email,phoneNumber,address);
        userDAO.updateUser(user);
        request.setAttribute("message","Update completed!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/user_view/edit_form.jsp");
        dispatcher.forward(request,response);
    }
}

