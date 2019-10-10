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
import java.util.List;

import static util.ApplicationConstant.*;
import static util.ApplicationDao.*;

@WebServlet(name = "UserCRServlet", urlPatterns = "/userCR")
public class UserCRServlet extends HttpServlet {
    private UserDao userDao = UserDaolmpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_IN);
        String password = request.getParameter(PASSWORD_IN);
        String button = request.getParameter(BUTTON_RESULT);
        String result;
        if (login.isEmpty() || password.isEmpty()) {
            result = VALUE_EMPTY;
        } else {
            User user = new User();
            boolean check = userDao.checkUser(login, password);
            if (button.equals(ADD_USER)) {
                if(check == false){
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(Role.USER);
                    userDao.create(user);
                    result = USER_ADD;
                }else {
                    result = USER_NOT_ADD;
                }
            } else {
                if (button.equals(DELETE_USER)){
                    user = userDao.getLogin(login, password);
                    if (user != null) {
                        User users = (User) request.getSession().getAttribute(USER_PARAM);
                        if(users.getLogin().equals(login)){
                            result = DONT_DELETE_ADMIN;
                        }else {
                            userDao.delete(user);
                            result = USER_DELETE;
                        }
                    } else {
                        result = USER_E;
                    }
                }else {
                    if(check == false){
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setRole(Role.ADMIN);
                        userDao.create(user);
                        result = ADD_ADMIN;
                    }else {
                       result = USER_NOT_ADD;
                    }
                }
            }
        }
        List<User> userList = userDao.getUserList();
        request.setAttribute(LIST, userList);
        request.setAttribute(RESULT, result);
        request.getRequestDispatcher(PAGE_MAIN).forward(request, response);
    }

}