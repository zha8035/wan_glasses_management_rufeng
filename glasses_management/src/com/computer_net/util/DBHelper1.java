package com.computer_net.util;

import java.sql.Connection;
import java.sql.SQLException;
//import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
//import org.apache.commons.dbutils.handlers.ScalarHandler;
//import org.apache.commons.lang.StringEscapeUtils;

import com.computer_net.model.ImagSave;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBHelper1 implements DBConfig {

    /*
     * 使用MySQL数据源获得数据库连接对象
     * 
     * @return：MySQL连接对象，如果获得失败返回null
     */
    public static Connection getConnection() {
        MysqlDataSource mds = new MysqlDataSource();// 创建MySQL数据源
        mds.setDatabaseName(databaseName);// 设置数据库名称
        mds.setUser(username);// 设置数据库用户名
        mds.setPassword(password);// 设置数据库密码
        try {
            return mds.getConnection();// 获得连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// 如果获取失败就返回null
    }

    /*
     * 判断指定用户名的用户是否存在
     * 
     * @return：如果存在返回true，不存在或者查询失败返回false
     */
    public static boolean exists(String username) {
        QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
        String sql = "select id from im_store where username = '" + username + "';";// 定义查询语句
        Connection conn = getConnection();// 获得连接
        ResultSetHandler<List<Object>> rsh = new ColumnListHandler();// 创建结果集处理类
        try {
            List<Object> result = runner.query(conn, sql, rsh);// 获得查询结果
            if (result.size() > 0) {// 如果列表中存在数据
                return true;// 返回true
            } else {// 如果列表中没有数据
                return false;// 返回false
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return false;// 如果发生异常返回false
    }

    /*
     * 验证用户名和密码是否正确 使用Commons Lang组件转义字符串避免SQL注入
     * 
     * @return：如果正确返回true，错误返回false
     */
    /*public static boolean check(String username, char[] password) {
        username = StringEscapeUtils.escapeSql(username);// 将用户输入的用户名转义
        QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
        String sql = "select password from im_store where username = '" + username + "';";// 定义查询语句
        Connection conn = getConnection();// 获得连接
        ResultSetHandler<Object> rsh = new ScalarHandler();// 创建结果集处理类
        try {
            String result = (String) runner.query(conn, sql, rsh);// 获得查询结果
            char[] queryPassword = result.toCharArray();// 将查询到得密码转换成字符数组
            if (Arrays.equals(password, queryPassword)) {// 如果密码相同则返回true
                Arrays.fill(password, '0');// 清空传入的密码
                Arrays.fill(queryPassword, '0');// 清空查询的密码
                return true;
            } else {// 如果密码不同则返回false
                Arrays.fill(password, '0');// 清空传入的密码
                Arrays.fill(queryPassword, '0');// 清空查询的密码
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return false;// 如果发生异常返回false

    }
    /*
     * 保存用户输入的注册信息
     * 
     * @return：如果保存成功返回true，保存失败返回false
     */
    public static boolean save(ImagSave IS) {
        QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
        String sql = "insert into im_store (username, cir, date,store) values (?, ?, ?,?);";// 定义查询语句
        Connection conn = getConnection();// 获得连接
        Object[] params = { IS.getUsername(), IS.getCir(), IS.getDate(),IS.getStore()};// 获得传递的参数
        try {
            int result = runner.update(conn, sql, params);// 保存用户
            if (result > 0) {// 如果保存成功返回true
                return true;
            } else {// 如果保存失败返回false
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return false;// 如果发生异常返回false
    }
}
