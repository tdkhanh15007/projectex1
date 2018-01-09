/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class ConnectionData {

    public Connection getConnection() {
        Connection conn = null;
        String host = "jdbc:sqlserver://localhost:1433;databaseName=Little";
        String User = "sa";
        String Pass = "sa";
        try {
            conn = DriverManager.getConnection(host, User, Pass);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
