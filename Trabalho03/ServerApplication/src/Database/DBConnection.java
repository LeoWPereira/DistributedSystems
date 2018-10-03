/**
 ******************************************************************************
 * @file    DBConnection.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Database;

import java.sql.*;

import com.mysql.jdbc.Statement;

public class DBConnection
{
	private static String status 			= "";
	
	private static String server 			= "192.185.176.93:3306/";
	
	private static String serverDatabase 	= "leonar14_DistributedSystems";
	
	private static String user 				= "leonar14_sisdist";
	
	private static String pass 				= "Leo.Pereira91";
	
	private static String className			= "com.mysql.jdbc.Driver";
	
	private static String passagesDBName	= "Passages";

	private static String hotelDBName		= "Hotels";
	
	private static String packagesDBName	= "Packages";
	
	public static Connection connectToDatabase()
	{
		Connection con = null;

		try
		{
			Class.forName(className);
			
			String url = "jdbc:mysql://" 	+ 
						 server 			+ 
						 serverDatabase 	+
						 "?user=" + user 	+
						 "&password=" + pass;
                        
			con = DriverManager.getConnection(url);
			
			status = "Connection Open";
		}
		catch(ClassNotFoundException e)
		{
			status = e.getMessage();
		}
		catch(SQLException e)
		{
			status = e.getMessage();
		}
		catch(Exception e)
		{
			status = e.getMessage();
		}
		
		System.out.println(status);
		
		return con;
	}
	
	public static Statement configureDatabase(Connection	_dbConnection) throws SQLException
	{
		_dbConnection	= (Connection)DBConnection.connectToDatabase();
		
		return (Statement)_dbConnection.createStatement();
	}
	
	public static String getDatabaseName()
	{
		return serverDatabase;
	}

	public static String getPassagesDBName()
	{
		return passagesDBName;
	}

	public static String getHotelDBName()
	{
		return hotelDBName;
	}

	public static String getPackagesDBName()
	{
		return packagesDBName;
	}
}