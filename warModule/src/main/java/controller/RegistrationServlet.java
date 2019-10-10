package controller;

import dao.UserDao;
import dao.UserDaolmpl;
import dto.Role;
import dto.User;
import util.ApplicationConstant;
import util.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ApplicationConstant.*;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao = UserDaolmpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ApplicationConstant.LOGIN_IN);
        String password = request.getParameter(ApplicationConstant.PASSWORD_IN);
        String result = "";
        if (login.isEmpty() || password.isEmpty()) {
            result = ApplicationConstant.VALUE_EMPTY;
        } else {
            boolean check = userDao.checkUser(login, password);
            if (check == false) {
                User user = new User(login, password, Role.USER);
                userDao.create(user);
                request.getSession().setAttribute(USER_PARAM, user);
                request.getRequestDispatcher(PAGE_MAIN).forward(request, response);
            } else {
                result = ApplicationDao.USER_NOT_ADD;
            }
        }
        request.setAttribute(RESULT, result);
        request.getRequestDispatcher(PAGE_INDEX).forward(request, response);
    }

}
