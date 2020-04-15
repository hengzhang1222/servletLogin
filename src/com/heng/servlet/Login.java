package com.heng.servlet;

import com.heng.dao.UserDao;
import com.heng.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        System.out.println(userName+":"+passWord);
        String login = userDao.login(userName, passWord);
        System.out.println(login);
        if ("登录成功".equals(login)) {
            response.setStatus(302);
            response.setHeader("Location", "index.html");
        }else {
            response.setStatus(302);
            response.setHeader("Location", "login.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
