package com.heng.util;

/**
 @author 恒   2020/4/14 10:07 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 获取连接对象,读取MySQL配置，关流
 */
public class jdbcUtil {
    private static String classDriver = null;
    private static String url = null;
    private static String name = null;
    private static String passWord = null;

     static {
        //创建属性配置对象
        Properties properties = new Properties();

        // String path = jdbcUtils.class.getClassLoader().getResource("").getPath();
        //获取路径,获取配置文件的输入流
       // InputStream inputStream = jdbcUtil.class.getClassLoader().getResourceAsStream("/D:/ZH/LearnPractice/JavaWeb/ServletLogin/web/mysql.properties");
        //读取配置文件
        try {
            properties.load(new FileInputStream(new File("/D:/ZH/LearnPractice/JavaWeb/ServletLogin/web/mysql.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取配置文件的类加载器
        classDriver = properties.getProperty("classDriver");
        //获取配置文件的url，name，passWord
        url = properties.getProperty("url");
        name = properties.getProperty("name");
        passWord = properties.getProperty("passWord");
    }

    /**
     获取连接对象
     @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            //驱动加载
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            con = DriverManager.getConnection(url, name, passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeAll(ResultSet rs, Statement ps, Connection conn) {
        closeResultSet(rs);
        closeStatement(ps);
        closeConnection(conn);
    }
    public static void closeAll( Statement ps, Connection conn) {
        closeStatement(ps);
        closeConnection(conn);
    }

    private static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
