package com.heng.servlet;

import com.heng.bean.User;
import com.heng.dao.UserDao;
import com.heng.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 注册号
 */
@WebServlet(name = "Register",urlPatterns = "/Register")
public class Register extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("userPassWord");
        String generd = request.getParameter("generd");
        String address = request.getParameter("address");
        User user = new User(userName,passWord,generd,address);
        String register = userDao.insertUser(user);
        System.out.println(register);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet (request, response);
    }
}
