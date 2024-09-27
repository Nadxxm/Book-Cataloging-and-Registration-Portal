package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/booklist")
public class BookList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 resp.setContentType("text/html");
	 PrintWriter writer = resp.getWriter();
	 Connection con = null;
	 Statement stmt = null;
	 ResultSet res = null;
	 String url = "jdbc:mysql:///book";
	 String un = "root";
	 String pwd = "root";
	 
	 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(url , un , pwd);
		 String query = "select * from bookdata";
		 stmt = con.createStatement();
		 res = stmt.executeQuery(query);
		
		 
		   writer.println("<table border='1' cellpadding='5' cellspacing='0'  class=\"container text-center\" >");
	        writer.println("<tr><th>ID</th><th>BOOKNAME</th><th>BOOKEDITION</th><th>BOOKPRICE</th><th>Edit</th><th>Delete</th></tr>");
		 
		 
		 while(res.next()) {
			 int id = res.getInt(1);
			 String bookname = res.getString(2);
			 String bookedition = res.getString(3);
			 float bookprice = res.getFloat(4);
		        // Add some rows to the table
		        
		        writer.println("<tr>");
                writer.println("<td>" + id + "</td>");
                writer.println("<td>" + bookname + "</td>");
                writer.println("<td>" + bookedition + "</td>");
                writer.println("<td>" + bookprice + "</td>");
                writer.println("<td><a href='Screen'>Edit</a></td>");
                writer.println("<td><a href=''>Delete</a></td>");
                writer.println("</tr>");
		 }
		    // End the table
	        writer.println("</table>");
	        writer.print("<a href='home.html'>Home</a>");
	} catch (Exception e) {
		e.printStackTrace();
		e.getMessage();
	   }
	
	}
}
	