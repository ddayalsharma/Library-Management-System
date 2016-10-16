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
public class student {

    Connection con;
    public String StuNo="";


    public student() {
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

    public int add(String stuno, String name, String branch, String year, String mail, String password,String biss,String bdt) {
        int s1 = 0;
        int stno = Integer.parseInt(stuno);
        String query = "insert into student values (?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, stno);
            ps.setString(2, name);
            ps.setString(3, branch);
            ps.setString(4, year);
            ps.setString(5, mail);
            ps.setString(6, password);
            ps.setString(7, biss);
            ps.setString(8, bdt);
            s1 = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //System.out.println("record");
        }
        return s1;
    }

    public int update(String stn, String name, String mail, String password) {
        int st = 0;
        int stno = Integer.parseInt(stn);
        String query = "update student set name=?,mail_id=?,password=? where stu_no=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, mail);
            ps.setString(3, password);
            ps.setInt(4, stno);
            st = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;

    } public int delall()
    {   int st=0;
        String query="delete from student";
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

    public int delete(String stn) {
        int st = 0;
        int stno=Integer.parseInt(stn);
        /*        System.out.println("enter student number");
         stno = sc.nextInt();*/
        String query = "delete from student where stu_no=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, stno);
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

    public int show(String stn) {
        int fid = 0, f = 0, stno = Integer.parseInt(stn);
        String fname = "", fbranch = "", fyear = "", fmail = "", fpas = "";

        String query = "select * from student where stu_no=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, stno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f = 1;
                fid = rs.getInt("stu_no");
                fname = rs.getString("name");
                fbranch = rs.getString("branch");
                fyear = rs.getString("year");
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

    public ResultSet showdetail(String stn) {
        int fid = 0;
        int stno = Integer.parseInt(stn);
        String fname = "", fmail = "", fpas = "";
        ResultSet rs=null;

        String query = "select name, mail_id, password from student where stu_no=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, stno);
            rs = ps.executeQuery();
   /*         while (rs.next()) {
                
                fid = rs.getInt("stu_no");
                fname = rs.getString("name");

                fmail = rs.getString("mail_id");
                fpas = rs.getString("password");
            }*/
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
        return rs;
    }
    
   public ResultSet check(String u_name,String pass)
    {
        int lid=Integer.parseInt(u_name);
        ResultSet rs=null;
        String query="select * from student where stu_no=?";
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
