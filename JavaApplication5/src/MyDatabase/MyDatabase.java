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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author A
 */
public class MyDatabase {

    private static Connection _connection;

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LittleAngels", "sa", "sa");
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
        if (!isConnected()) {
            connect();
        }

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

    public boolean checkSyb(String s) {
        boolean check = false;
        char syb[] = {'-', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '='};
        char a[] = s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < syb.length; j++) {
                if (a[i] == syb[j]) {
                    check = true;
                }
            }
        }
        return check;
    }

    public boolean checkStart(String s) {
        boolean check = false;
        char syb[] = {'-', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '='};
        char a[] = s.toCharArray();
        for (int i = 0; i < syb.length; i++) {
            if (a[0] == syb[i]) {
                check = true;
                break;
            }

        }
        return false;
    }

    public boolean checkPat(String s) {
        Pattern p = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
    public boolean checkEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        }
        return false;
    }

    public boolean checkFone(String phone) {
        if (phone.startsWith("0") && phone.length() > 9 && phone.length() < 12) {
            return true;
        }
        return false;
    }
    
    public boolean checkHaveNum(String str){
        char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        char[] toChar = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            toChar[i] = str.charAt(i);
            for (int j = 0; j < nums.length; j++) {
                if (toChar[i] == nums[j]) { return true; }
            }
        }
        return false;
    }
}
