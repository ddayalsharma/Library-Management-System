/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deepa Dayal Sharma
 */
public class admin {

    Connection con;

    public admin() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library",
                    "root", "root");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("error" + e);
        }
    }

    public int updatead(String pass, String npass) {
        int s = 0;
        try {
            String query = "update admin set pass=? where pass=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, npass);
            ps.setString(2, pass);
            s = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public int bookad(String acno, String bnm, String bau, String bsu, String bqt) {
        int s = 0;
        try {

            String query = "insert into book values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, acno);
            ps.setString(2, bnm);
            ps.setString(3, bau);
            ps.setString(4, bsu);
            ps.setString(5, bqt);
            s = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
}
