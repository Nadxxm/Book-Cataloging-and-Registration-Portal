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

import com.mysql.cj.xdevapi.PreparableStatement;

@WebServlet("/register")
public class ResgisterServlet extends HttpServlet {
	 private static final String query = "INSERT INTO bookdata (BOOKNAME , BOOKEDITION , BOOKPRICE) VALUES(? , ? , ?) ";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet res = null;
       String url = "jdbc:mysql:///book";
       String un = "root";
       String pwd = "root";
      
		//get PrintWriter
		PrintWriter writer = resp.getWriter();
		//Set Content type
		resp.setContentType("text/html");
		//Load jdbc Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection(url , un , pwd);
		    pstmt = con.prepareStatement(query);
		    String bookname = req.getParameter("bookName");
		    String bookedition = req.getParameter("bookEdition");
		    float bookprice = Float.parseFloat(req.getParameter("bookPrice"));
		    pstmt.setString(1, bookname);
		    pstmt.setString(2, bookedition);
		    pstmt.setFloat(3, bookprice);
		    
		    int count = pstmt.executeUpdate();
		    
		    if(count == 1) {
		    	writer.print("<h3>Book Registered Sucessfully</h3>");
		    }else {
		    	writer.print("<h3>Book Not Registered Sucessfully</h3>");
		    }
		    
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		//generate connection 
		writer.print("<a href='home.html'>Home</a>");
		writer.print("<br>");
		writer.print("<a href='booklist'>Book List</a>");
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
	}

}
