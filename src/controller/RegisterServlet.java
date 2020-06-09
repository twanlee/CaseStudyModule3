package controller;

import dao.impl.UserDAOImpl;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDAOImpl userDAO = new UserDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        addUser(request,response);
        response.setContentType("text/html;character=utf-8");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showRegisterForm(request,response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        User user = new User(username,password,firstName,lastName,email,phoneNumber,address);
        if(userDAO.addUser(user)){
            request.setAttribute("message","Creation completed!");
        }else {
            request.setAttribute("error","Duplicate name or lack of field. Please check again");
        }
        request.getRequestDispatcher("view/user_view/user_register_form.jsp").forward(request,response);

    }
    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/user_view/user_register_form.jsp");
        dispatcher.forward(request,response);
    }
}
