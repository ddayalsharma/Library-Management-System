/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deepa Dayal Sharma
 */
public class booksearch {

    Connection con;
    public String booknm = "";

    public booksearch() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/library",
                    "root", "root");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultSet searchsub() {
        ResultSet rs = null;
        String query = "select bsub from book";
        try {
            PreparedStatement ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            /*   while (rs.next()) {
             f = 1;
             fid = rs.getInt("l_id");
             fname = rs.getString("name");
             fmail = rs.getString("mail_id");
             fpas = rs.getString("password");
             }*/

        } catch (Exception e) {

            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet ssub(String su) {
        ResultSet rs = null;
        String query = "select bname from book where bsub=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, su);
            rs = ps.executeQuery();
            /*   while (rs.next()) {
             f = 1;
             fid = rs.getInt("l_id");
             fname = rs.getString("name");
             fmail = rs.getString("mail_id");
             fpas = rs.getString("password");
             }*/

        } catch (Exception e) {

            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchnm() {
        ResultSet rs = null;
        String query = "select bname from book";
        try {
            PreparedStatement ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            /*   while (rs.next()) {
             f = 1;
             fid = rs.getInt("l_id");
             fname = rs.getString("name");
             fmail = rs.getString("mail_id");
             fpas = rs.getString("password");
             }*/

        } catch (Exception e) {

            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet searchaut() {
        ResultSet rs = null;
        String query = "select bauthor from book";
        try {
            PreparedStatement ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            /*   while (rs.next()) {
             f = 1;
             fid = rs.getInt("l_id");
             fname = rs.getString("name");
             fmail = rs.getString("mail_id");
             fpas = rs.getString("password");
             }*/

        } catch (Exception e) {

            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet searchauth(String aut) {
        ResultSet rs = null;
        String query = "select bname from book where bauthor=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, aut);
            rs = ps.executeQuery();
            /*   while (rs.next()) {
             f = 1;
             fid = rs.getInt("l_id");
             fname = rs.getString("name");
             fmail = rs.getString("mail_id");
             fpas = rs.getString("password");
             }*/

        } catch (Exception e) {

            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet check(String bnm) {
        ResultSet rs = null;
        String query = "select bqty from book where bname=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bnm);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(booksearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public int bmin(String bnm) {
        int s=0;
        try {
            
            String query = "update book set bqty=bqty-1 where bname=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bnm);
            s = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(booksearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

}
