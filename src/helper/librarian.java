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
import java.util.Scanner;

/**
 *
 * @author Deepa Dayal Sharma
 */
public class librarian {

    Connection con;

    public librarian() {
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

    public int add(String libid, String name, String mail, String password) {
        int s1 = 0;
        int lid = Integer.parseInt(libid);
//        System.out.println("enter student number");
//        stno = sc.nextInt();
//        System.out.println("enter student name");
//        name = sc.next();
//        System.out.println("enter branch");
//        branch = sc.next();
//        System.out.println("enter year");
//        year = sc.next();
//        System.out.println("enter e mail id");
//        mail=sc.next();
//        System.out.println("enter password");
//        password = sc.next();
        String query = "insert into librarian values (?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, lid);
            ps.setString(2, name);
            ps.setString(3, mail);
            ps.setString(4, password);
            s1 = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s1;
    }

    public int update(String lid, String password, String mail) {
        int st = 0, l_id = Integer.parseInt(lid);
        String query;
        //  System.out.println("enter student number");
        //  stno = sc.nextInt();
        // System.out.println("update password");
        //  sc.next();
        // System.out.println("enter b to update department");
        // System.out.println("enter c to update salary");
        // System.out.println("enter your choice");
        if (mail.equals("")) {
            query = "update librarian set password=? where l_id=?";
        }
        if (password.equals("")) {
            query = "update librarian set mail=? where l_id=?";
        } else {
            query = "update librarian set password=? , mail_id=? where l_id=?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, mail);
            ps.setInt(3, l_id);
            st = ps.executeUpdate();
            /* if (st == 1) {
             System.out.println("record updated");
             } else {
             System.out.println("record not updated");
             }*/
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;

    }

    public int delete(String l_id) {
        int lid=Integer.parseInt(l_id);
        int st = 0;
        /*        System.out.println("enter student number");
         stno = sc.nextInt();*/
        String query = "delete from librarian where l_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, lid);
            st = ps.executeUpdate();
            /*        if (st == 1) {
             System.out.println("record deleted");
             } else {
             System.out.println("record not deleted");
             }*/
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }
    public int delall()
    {   int st=0;
        String query="delete from librarian";
        try{
        PreparedStatement ps=con.prepareStatement(query);
        st=ps.executeUpdate();
    }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return st;
    }
    public int show(int lid) {
        int fid = 0, f = 0;
        String fname = "", fmail = "", fpas = "";
        //      System.out.println("enter student number");
        //      stno = sc.nextInt();
        String query = "select * from librarian where l_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, lid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f = 1;
                fid = rs.getInt("l_id");
                fname = rs.getString("name");
                fmail = rs.getString("mail_id");
                fpas = rs.getString("password");
            }
            /*            if (f == 1) {
             System.out.println("e_id=" + fid);
             System.out.println("e_name=" + fname);
             System.out.println("e_dept=" + fdept);
             System.out.println("e_salary=" + fsal);
             } else {
             System.out.println("record not found");
             */        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return f;
    }
    public ResultSet checklib(String u_name,String pass)
    {
        int lid=Integer.parseInt(u_name);
        ResultSet rs=null;
        String query="select * from librarian where l_id=?";
        try
        {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,lid);
            rs=ps.executeQuery();
 /*           while(rs.next())
            {
                name=rs.getInt("l_id");
                passw=rs.getString("password");
                
            }*/
        }
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        return rs;
                    
        }
    

    /*    public void showall() {
     int fid;
     String fname = "", fbranch = "",fyear="",fmail="",fpass="";
     String query = "select * from student";
     try {
     PreparedStatement ps = con.prepareStatement(query);
     ResultSet rs = ps.executeQuery();
     while (rs.next()) {
     fid = rs.getInt("stu_id");
     fname = rs.getString("name");
     fbranch = rs.getString("branch");
     fyear = rs.getString("year");
     fmail=rs.getString("mail_id");
     fpass=rs.getString("password");
     System.out.println("e_id=" + fid);
     System.out.println("e_name=" + fname);
     System.out.println("e_dept=" + fdept);
     System.out.println("e_salary=" + fsal);

     }
     } catch (SQLException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }

     }
     */
}
