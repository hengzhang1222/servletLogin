package com.heng.zhtest;

import com.heng.bean.User;
import com.heng.dao.UserDao;
import com.heng.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;

/**
 @author 恒   2020/4/14 16:40 */

public class TestDaoImpl {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void testLogin() {
        String message = userDao.login("王五", "456");
        System.out.println(message);
    }

    @Test
    public void testFindAllByAdmin() {
        List<User> list = userDao.findAllByAdmin("admin", "admin");
        for (User u : list) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindById() {
        User byId = userDao.findById(2);
        System.out.println(byId);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setName("赵六");
        user.setPassWord("789");
        user.setGenerd("女");
        user.setAddress("广东");
        String s = userDao.insertUser(user);
        System.out.println(s);
    }
    /*
        userDao.deleteUserById();
     */
    @Test
    public void testUpdateUserInfo(){
        User user = new User();
        user.setName("赵六");
        user.setPassWord("789");
        user.setGenerd("男");
        user.setAddress("佛山");
        User userInfo = userDao.updateUserInfo(user, 9);
        System.out.println(userInfo);
    }
    @Test
    public void testDeleteUserById(){
        String s = userDao.deleteUserById(9);
        System.out.println(s);
    }
}
