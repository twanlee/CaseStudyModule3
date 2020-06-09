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
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        try {
            loginCheck(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showLoginForm(request, response);
    }

    private void loginCheck(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDAO.findByUserName(username);
        int auth = userDAO.checkAuth(user.getUserId());
        if (user != null && password.equals(user.getPassword())) {
            if (auth == 1) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/admin_view/admin_menu.jsp");
                dispatcher.forward(request, response);
            } else if (auth == 2) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/staff_view/staff_menu.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/user_view/user_menu.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            request.setAttribute("error", "Invalid username or password!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/user_view/login_form.jsp");
            dispatcher.forward(request, response);
        }


    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/user_view/login_form.jsp");
        dispatcher.forward(request, response);

    }
}
