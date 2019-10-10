package controller;

import util.ApplicationConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.ApplicationConstant.PAGE_INDEX;

@WebServlet(name = "UserOutServlet", urlPatterns = "/userOut")
public class UserOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher(PAGE_INDEX).forward(request, response);
    }
}

