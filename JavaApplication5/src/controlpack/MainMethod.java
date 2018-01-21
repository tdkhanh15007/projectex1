/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlpack;

import javax.swing.JDialog;

/**
 *
 * @author K
 */
public class MainMethod {
    public void displaydialog(JDialog jdl) {
        jdl.setVisible(true);
        jdl.pack();
        jdl.setLocationRelativeTo(null);
    }
}
