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

/**
 * @brief
 */
public class DBConnection
{
	/**
	 * @brief
	 */
	private static String status 			= "";
	
	/**
	 * @brief
	 */
	private static String server 			= "192.185.176.93:3306/";
	
	/**
	 * @brief
	 */
	private static String serverDatabase 	= "leonar14_DistributedSystems";
	
	/**
	 * @brief
	 */
	private static String user 				= "leonar14_sisdist";
	
	/**
	 * @brief
	 */
	private static String pass 				= "Leo.Pereira91";
	
	/**
	 * @brief
	 */
	private static String className			= "com.mysql.jdbc.Driver";
	
	/**
	 * @brief
	 */
	private static String passagesDBName	= "Passages";

	/**
	 * @brief
	 */
	private static String hotelDBName		= "Hotels";
	
	/**
	 * @brief
	 */
	private static String packagesDBName	= "Packages";
	
	/**
	 * @brief
	 */
	public static Connection connectToDatabase()
	{
		Connection con = null;

		try
		{
			Class.forName(className);
			
			String url = "jdbc:mysql://" 	+ 
						 server 			+ 
						 serverDatabase;
                        
			con = DriverManager.getConnection(url,
											  user,
											  pass);
			
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
	
	/**
	 * @brief	Initial database connection settings
	 * 
	 * @return	Consult STM
	 */
	public static Statement configureDatabase(Connection	_dbConnection) throws SQLException
	{
		_dbConnection	= (Connection)DBConnection.connectToDatabase();

		return _dbConnection.createStatement();
	}
	
	/**
	 * @brief
	 * 
	 * @return
	 */
	public static String getDatabaseName()
	{
		return serverDatabase;
	}
	
	/**
	 * @brief
	 * 
	 * @return
	 */
	public static String getPassagesDBName()
	{
		return passagesDBName;
	}
	
	/**
	 * @brief
	 * 
	 * @return
	 */
	public static String getHotelDBName()
	{
		return hotelDBName;
	}
	
	/**
	 * @brief
	 * 
	 * @return
	 */
	public static String getPackagesDBName()
	{
		return packagesDBName;
	}
}