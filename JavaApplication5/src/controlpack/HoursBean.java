/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlpack;

import MyDatabase.MyDatabase;
import java.sql.Connection;

/**
 *
 * @author K
 */
public class HoursBean {
    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
}
