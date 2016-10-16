/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author Deepa Dayal Sharma
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class help {
    Connection con;
    public String booknm,StuNo;
    
    public help() {
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
    
    
    
    public ResultSet searchsub() 
    {
        ResultSet rs = null;
        String query = "select bsub from book";
        try 
        {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;

    }

    
    
    public ResultSet ssub(String su) 
    {
        ResultSet rs = null;
        String query = "select bname from book where bsub=?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, su);
            rs = ps.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    
    
    public ResultSet searchnm() 
    {
        ResultSet rs = null;
        String query = "select bname from book";
        try 
        {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;

    }

    
    
    public ResultSet searchaut() 
    {
        ResultSet rs = null;
        String query = "select bauthor from book";
        try 
        {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    
    
    public ResultSet searchauth(String aut)
    {
        ResultSet rs = null;
        String query = "select bname from book where bauthor=?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, aut);
            rs = ps.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;

    }

    
    
    public ResultSet check(String bnm)
    {
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

    
    
    public int bmin(String bnm)
    {
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

    
    public int add(String libid, String name, String mail, String password)
    {
        int s1 = 0;
        int lid = Integer.parseInt(libid);
        String query = "insert into librarian values (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, lid);
            ps.setString(2, name);
            ps.setString(3, mail);
            ps.setString(4, password);
            s1 = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s1;
    }


    
    public int update(String lid, String password, String mail) 
    {
        int st = 0, l_id = Integer.parseInt(lid);
        String query;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;

    }

    
    
    public int delete(String l_id) 
    {
        int lid=Integer.parseInt(l_id);
        int st = 0;
        String query = "delete from librarian where l_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, lid);
            st = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
    
    
    
    public int delall()
    {
        int st=0;
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
            } catch (Exception e) {
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
        }
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        return rs;
                    
        }
    
    
    public int add(String stuno, String name, String branch, String year, String mail, String password,String biss,String bdt) 
    {
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
            e.printStackTrace();
        }
        return s1;
    }


    
    
    public int update(String stn, String name, String mail, String password) 
    {
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
            e.printStackTrace();
        }
        return st;

    } 
    

    
    
    public int delallst()
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

    
    
    
    public int deletest(String stn) {
        int st = 0;
        int stno=Integer.parseInt(stn);
        String query = "delete from student where stu_no=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, stno);
            st = ps.executeUpdate();
        } catch (Exception e) {
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
                     } catch (Exception e) {
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
                  } catch (Exception e) {
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
        }
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        return rs;
                    
        }
    
    
    
    public int updatead(String pass, String npass) 
    {
        int s = 0;
        try {
            String query = "update admin set pass=? where uname=? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, npass);
            ps.setString(2, "DEEPA");
            s = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    
    
    public int bookad(String acno, String bnm, String bau, String bsu, String bqt) 
    {
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
    
    
    
     public int bookissue(String sid, String idate, String bnm) 
    {
        int s = 0;
        try {

            String query = "update student set bissue=? , issuedate=? where stu_no=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bnm);
            ps.setString(2, idate);
            ps.setInt(3, Integer.parseInt(sid));
            s = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
     
     
     
     public ResultSet searchstu(String u_name)
    {
        int lid=Integer.parseInt(u_name);
        ResultSet rs=null;
        String query="select name,branch,year,bissue,issuedate from student where stu_no=?";
        try
        {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,lid);
            rs=ps.executeQuery();
        }
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        return rs;
                    
        }
    
}
