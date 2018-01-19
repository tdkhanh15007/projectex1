/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author A
 */
public class MyDatabase {
    
    private static Connection _connection;
    
    private static void connect() throws ClassNotFoundException, SQLException {
        
        String username = "sa";
        String password = "sa";
        String sqlURL = "jdbc:sqlserver://localhost:1433;databaseName=LittleAngels";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        _connection = DriverManager.getConnection(sqlURL, username, password);
        
    }
    
    private static boolean isConnected() throws SQLException {
        return _connection != null && !_connection.isClosed();
    }
    
    public static boolean checkLogin(String userMail, String password) throws SQLException, ClassNotFoundException {
        
        // Kiem tra ket noi
        if (!isConnected()) connect();
        
        // Lam viec voi CSDL
        String sqlQuery = "SELECT * FROM [User] where user_email = ? and password = ?";
        PreparedStatement ps;
        ResultSet rs;        
        ps = _connection.prepareStatement(sqlQuery);
        ps.setString(1, userMail);
        ps.setString(2, password);
        rs = ps.executeQuery();        
        return rs.next();
    }
    
    public static ArrayList<String> getListCusEmail() throws SQLException, ClassNotFoundException {
        
        if (!isConnected()) connect();
        
        
        // Lam viec voi CSDL
        String sqlQuery = "SELECT cus_email FROM [Customer]";
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String> dsCusEmail = new ArrayList<>();
        
        ps = _connection.prepareStatement(sqlQuery);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            dsCusEmail.add(rs.getString("cus_email"));
        }
        return dsCusEmail;
        
    }
    
}
