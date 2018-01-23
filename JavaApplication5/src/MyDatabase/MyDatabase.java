/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDatabase;

import controlpack.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A
 */
public class MyDatabase {
    
    private static Connection _connection;
    
    public Connection getConnection(){
        Connection conn =  null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LittleAngels", "sa", "sa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
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
        String sqlQuery = "SELECT * FROM [Users] where user_email = ? and password = ?";
        PreparedStatement ps;
        ResultSet rs;        
        ps = _connection.prepareStatement(sqlQuery);
        ps.setString(1, userMail);
        ps.setString(2, password);
        rs = ps.executeQuery();  
//        while(rs.next()){
//            UserBean us = new UserBean();
//            us.setUser(rs.getString("user_email"));
//            us.setRole(rs.getBoolean("role"));
//        }

        return rs.next();
    }    
}
