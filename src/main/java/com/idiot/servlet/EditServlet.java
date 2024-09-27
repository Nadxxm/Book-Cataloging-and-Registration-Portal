package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet res = null;
       String url = "jdbc:mysql:///book";
       String un = "root";
       String pwd = "root";
       resp.setContentType("text/html");
       PrintWriter writer = resp.getWriter();
       
       try {
    	   Class.forName("com.mysql.cj.jdbc.Driver");
    	   con = DriverManager.getConnection(url, un , pwd);
    	   String id = req.getParameter("id");
    	   String name = req.getParameter("bookName");
    	   String edit = req.getParameter("bookEdition");
    	   float price = Float.parseFloat(req.getParameter("bookPrice"));
    	   String query = "UPDATE bookdata SET BOOKNAME = ? , BOOKEDITION = ? , BOOKPRICE = ? WHERE id = ?";
    	   pstmt = con.prepareStatement(query);
    	   pstmt.setString(1, name);
    	   pstmt.setString(2, edit);
    	   pstmt.setFloat(3, price);
    	   pstmt.setString(4 , id);
    	   int update = pstmt.executeUpdate();
    	   
    	   if(update > 0) {
    		   writer.print("<h3>Record is Edited</h3>");
    		   writer.println("<a href='home.html'>Home</a><br>");
    	       writer.println("<a href='booklist'>Book List</a>");
    	   }else {
    		   writer.print("<h3>Record is not Edited</h3>");
    		   writer.println("<a href='home.html'>Home</a><br>");
    	       writer.println("<a href='booklist'>Book List</a>");
    	   }
    	   
    	   
		
	} catch (Exception e) {
		e.printStackTrace();
	}
       
	}
	
}
