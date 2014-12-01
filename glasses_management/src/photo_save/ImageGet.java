package photo_save;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ImageGet {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String user = "root";
        String password = "3041992";
        String url = "jdbc:mysql://localhost:3306/db_database01?characterEncoding=utf-8";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = null;
        ResultSet resultSet = null;
        InputStream inputStream = null;
        try {
            statement = connection.createStatement();
            String sql = "select p.photo from photo p where id = 1";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            inputStream = resultSet.getBinaryStream("photo");
            ImageUtil.readBlob(inputStream, "E:\\1.jpg");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (statement != null)
                        if (statement != null)
                            try {
                                statement.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } finally {
                                if (connection != null)
                                    try {
                                        connection.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                            }
                }
            }
        }
    }
}