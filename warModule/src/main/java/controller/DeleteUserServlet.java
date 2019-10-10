package controller;

import dao.UserDao;
import dao.UserDaolmpl;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.ApplicationConstant.*;
import static util.ApplicationDao.DONT_DELETE_ADMIN;
import static util.ApplicationDao.USER_DELETE;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserDao userDao = UserDaolmpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter(USER_ID));
        String result;
        if (id != null) {
            User users = (User) request.getSession().getAttribute(USER_PARAM);
            if (users.getId() != id) {
                User user = userDao.get(id);
                if (user != null) {
                    userDao.delete(user);
                    result = USER_DELETE;
                } else {
                    result = DONT_DELETE_ADMIN;
                }
            } else {
                result = DONT_DELETE_ADMIN;
            }
        } else {
            result = VALUE_EMPTY;
        }
        List<User> userList = userDao.getUserList();
        request.setAttribute(LIST, userList);
        request.setAttribute(RESULT, result);
        request.getRequestDispatcher(PAGE_MAIN).forward(request, response);
    }
}
