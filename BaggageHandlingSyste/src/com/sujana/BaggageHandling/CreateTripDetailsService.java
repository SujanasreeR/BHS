package com.sujana.BaggageHandling;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTripDetails
 */
@WebServlet("/CreateTripDetails")
public class CreateTripDetailsService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTripDetailsService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String airportLocations = request.getParameter("AirportLocations");
		String[] airportLocationValues = airportLocations.split(",");
		
    	Connection con = (Connection) getServletContext().getAttribute("DBConnection");
    	
    	try {
    		con.setAutoCommit(false);
    		PreparedStatement tps = con.prepareStatement("SELECT COALESCE(MAX( TripId ),0) AS maxId FROM CreateTripDetails");
    		ResultSet rs = tps.executeQuery();
    		rs.next();
    		int tripid = rs.getInt("maxId")+1;
    		for(int i = 0; i<airportLocationValues.length; i ++) {
    			int stopnumber = i+1;
    			
    			PreparedStatement ps = con.prepareStatement("INSERT INTO CreateTripDetails (TripId,StopNumber,AirportLocations)\n" + 
    	    			"VALUES ( '"+ tripid+"', '"+ stopnumber + "', '"+ airportLocationValues[i] +"'); " );
    			ps.executeUpdate();
    		}
    		con.commit();
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    		//con.rollback();
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
