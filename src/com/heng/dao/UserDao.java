package com.heng.dao;

import com.heng.bean.User;

import java.util.List;

/**
 @author 恒   2020/4/14 14:08 */

public interface UserDao {
    /**
     查询所有
     @return
     */
    List<User> findAllByAdmin(String userName, String passWord);

    /**
     通过id查询个人信息
     @param id
     @return
     */
    User findById(int id);

    /**
     更新用户信息
     @param user
     @return
     */
    User updateUserInfo(User user, int id);

    /**
     注册用户
     @param user
     @return 成功或失败
     */
    String insertUser(User user);

    /**
     删除用户通过id
     @param id
     @return 成功或失败
     */
    String deleteUserById(int id);

    /**
     登录
     @param userName
     @param passWord
     @return 登录成功或失败
     */
    String login(String userName, String passWord);
}
