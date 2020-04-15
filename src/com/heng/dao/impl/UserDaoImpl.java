package com.heng.dao.impl;

import com.heng.bean.User;
import com.heng.dao.UserDao;
import com.heng.util.jdbcUtil;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 @author 恒   2020/4/14 14:33 */
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAllByAdmin(String userName, String passWord) {
        List<User> list = null;
        if ("admin".equals(userName) && "admin".equals(passWord)) {
            list = findAll();
        } else {
            System.out.println("不是管理员用户");
        }
        return list;
    }

    @Override
    public User findById(int id) {
        User user = findId(id);
        return user;
    }

    @Override
    public User updateUserInfo(User user, int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "update usertable set userName = ? ,userPassWord = ?,generd=?,address=? where id =? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(5, id);
            ps.setString(4, user.getAddress());
            ps.setString(3, user.getGenerd());
            ps.setString(2, user.getPassWord());
            ps.setString(1, user.getName());
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("跟新成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(ps, conn);
        }
        return findId(id);
    }

    @Override
    public String insertUser(User user) {
        Connection conn = conn = jdbcUtil.getConnection();
        String sql = "INSERT into usertable (userName,userPassWord,generd,address) values (?,?,?,?)";
        String massage = "";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getGenerd());
            ps.setString(4, user.getAddress());
            int i = ps.executeUpdate();
            if (i > 0) {
                massage = "注册成功";
            } else {
                massage = "注册失败";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(ps, conn);
        }
        return massage;
    }

    @Override
    public String deleteUserById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String massage = "";
        try {
            conn = jdbcUtil.getConnection();
            String sql = "delete from usertable where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i > 0) {
                massage = "删除成功";
            } else {
                massage = "删除失败";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(ps, conn);
        }
        return massage;
    }

    @Override
    public String login(String userName, String passWord) {
        List<User> list = findAll();
        String massage = "";
        for (User u : list) {
            if (u.getName().equals(userName) && u.getPassWord().equals(passWord)) {
                massage = "登录成功";
                break;
            } else {
                massage = "登录失败";
            }
        }
        return massage;
    }

    private User findId(int id) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "select  *  from usertable where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                getUser(user, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeAll(rs, ps, conn);
        }
        return user;
    }


    /**
     私有方法，仅供内部使用，查询所有用户信息
     @return
     */
    private List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "select * from usertable";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                getUser(user, rs);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void getUser(User user, ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String userName = rs.getString("userName");
        String userPassWord = rs.getString("userPassWord");
        String generd = rs.getString("generd");
        String address = rs.getString("address");
        user.setName(userName);
        user.setPassWord(userPassWord);
        user.setGenerd(generd);
        user.setAddress(address);
    }
}
   /* private void getUserInfo(User user, String sql) {
        Connection conn = jdbcUtil.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getGenerd());
            ps.setString(4, user.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
