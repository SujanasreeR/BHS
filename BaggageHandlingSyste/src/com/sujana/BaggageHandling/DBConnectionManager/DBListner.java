package com.sujana.BaggageHandling.DBConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListner implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	String dbURL = "jdbc:mysql://127.0.0.1:3306/BHS";
    	String user = "root";
    	String pwd = "******";//Your Password Here
    	
    	try {
    		Connection connection = DriverManager.getConnection(dbURL, user, pwd);
			ctx.setAttribute("DBConnection", connection);
			System.out.println("DB Connection initialized successfully.");
		}  catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
    	try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
