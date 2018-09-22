/**
 ******************************************************************************
 * @file    Connection.java
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
public class Connection
{
	/**
	 * @brief
	 */
	public static String status 			= "";
	
	/**
	 * @brief
	 */
	public static String server 			= "192.185.176.93:3306/";
	
	/**
	 * @brief
	 */
	public static String serverDatabase 	= "leonar14_DistributedSystems";
	
	/**
	 * @brief
	 */
	public static String user 				= "leonar14_sisdist";
	
	/**
	 * @brief
	 */
	public static String pass 				= "Leo.Pereira91";
	
	/**
	 * @brief
	 */
	public static String className			= "com.mysql.jdbc.Driver";

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
						 serverDatabase 	+
						 "?user=" + user 	+
						 "&password=" + pass;
                        
			con = (Connection)DriverManager.getConnection(url);
			
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
		
		return con;
	}	
}