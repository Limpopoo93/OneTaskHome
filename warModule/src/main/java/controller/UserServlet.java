package controller;

import dao.UserDao;
import dao.UserDaolmpl;
import dto.Role;
import dto.User;
import util.ApplicationConstant;
import util.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.ApplicationConstant.*;

@WebServlet(name = "UserServlet", urlPatterns = "/user",
        initParams ={
                @WebInitParam(name = ADMIN_LOGIN_FIRST, value = ADMIN_LOGIN_FIRST),
                @WebInitParam(name = ADMIN_PASSWORD_FIRST, value = ADMIN_PASSWORD_FIRST),
                @WebInitParam(name = ADMIN_LOGIN_SECOND, value = ADMIN_LOGIN_SECOND),
                @WebInitParam(name = ADMIN_PASSWORD_SECOND, value = ADMIN_PASSWORD_SECOND)
        })
public class UserServlet extends HttpServlet {
    private UserDao userDao = UserDaolmpl.getInstance();
    private List<User> userList = userDao.getUserList();

    @Override
    public void init() throws ServletException {
        userDao.create(new User(getInitParameter(ADMIN_LOGIN_FIRST), getInitParameter(ADMIN_PASSWORD_FIRST), Role.ADMIN));
        userDao.create(new User(getInitParameter(ADMIN_LOGIN_SECOND), getInitParameter(ADMIN_PASSWORD_SECOND), Role.ADMIN));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN_IN);
        String password = request.getParameter(PASSWORD_IN);
        if (login.isEmpty() || password.isEmpty()) {
            request.setAttribute(RESULT, VALUE_EMPTY);
            request.getRequestDispatcher(PAGE_INDEX).forward(request, response);
        } else {
            User user = userDao.getLogin(login, password);
            if (user != null) {
                request.setAttribute(LIST, userList);
                request.getSession().setAttribute(USER_LOGIN_FIRST, user);
                request.getRequestDispatcher(PAGE_MAIN).forward(request, response);
            } else {
                request.setAttribute(RESULT, ApplicationDao.USER_E);
                request.getRequestDispatcher(PAGE_INDEX).forward(request, response);
            }
        }

    }
}
