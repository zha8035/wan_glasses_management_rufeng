package com.computer_net.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringEscapeUtils;

import com.computer_net.model.User;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBHelper implements DBConfig {

    /*
     * ʹ��MySQL����Դ������ݿ����Ӷ���
     * 
     * @return��MySQL���Ӷ���������ʧ�ܷ���null
     */
    public static Connection getConnection() {
        MysqlDataSource mds = new MysqlDataSource();// ����MySQL����Դ
        mds.setDatabaseName(databaseName);// �������ݿ�����
        mds.setUser(username);// �������ݿ��û���
        mds.setPassword(password);// �������ݿ�����
        try {
            return mds.getConnection();// �������
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// �����ȡʧ�ܾͷ���null
    }

    /*
     * �ж�ָ���û������û��Ƿ����
     * 
     * @return��������ڷ���true�������ڻ��߲�ѯʧ�ܷ���false
     */
    public static boolean exists(String username) {
        QueryRunner runner = new QueryRunner();// ����QueryRunner����
        String sql = "select id from tb_user where username = '" + username + "';";// �����ѯ���
        Connection conn = getConnection();// �������
        ResultSetHandler<List<Object>> rsh = new ColumnListHandler();// ���������������
        try {
            List<Object> result = runner.query(conn, sql, rsh);// ��ò�ѯ���
            if (result.size() > 0) {// ����б��д�������
                return true;// ����true
            } else {// ����б���û������
                return false;// ����false
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// �ر�����
        }
        return false;// ��������쳣����false
    }

    /*
     * ��֤�û����������Ƿ���ȷ ʹ��Commons Lang���ת���ַ�������SQLע��
     * 
     * @return�������ȷ����true�����󷵻�false
     */
    public static boolean check(String username, char[] password) {
        username = StringEscapeUtils.escapeSql(username);// ���û�������û���ת��
        QueryRunner runner = new QueryRunner();// ����QueryRunner����
        String sql = "select password from tb_user where username = '" + username + "';";// �����ѯ���
        Connection conn = getConnection();// �������
        ResultSetHandler<Object> rsh = new ScalarHandler();// ���������������
        try {
            String result = (String) runner.query(conn, sql, rsh);// ��ò�ѯ���
            char[] queryPassword = result.toCharArray();// ����ѯ��������ת�����ַ�����
            if (Arrays.equals(password, queryPassword)) {// ���������ͬ�򷵻�true
                Arrays.fill(password, '0');// ��մ��������
                Arrays.fill(queryPassword, '0');// ��ղ�ѯ������
                return true;
            } else {// ������벻ͬ�򷵻�false
                Arrays.fill(password, '0');// ��մ��������
                Arrays.fill(queryPassword, '0');// ��ղ�ѯ������
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// �ر�����
        }
        return false;// ��������쳣����false

    }

    /*
     * �����û������ע����Ϣ
     * 
     * @return���������ɹ�����true������ʧ�ܷ���false
     */
    public static boolean save(User user) {
        QueryRunner runner = new QueryRunner();// ����QueryRunner����
        String sql = "insert into tb_user (username, password, email) values (?, ?, ?);";// �����ѯ���
        Connection conn = getConnection();// �������
        Object[] params = { user.getUsername(), user.getPassword(), user.getEmail() };// ��ô��ݵĲ���
        try {
            int result = runner.update(conn, sql, params);// �����û�
            if (result > 0) {// �������ɹ�����true
                return true;
            } else {// �������ʧ�ܷ���false
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// �ر�����
        }
        return false;// ��������쳣����false
    }
}
