/**
 ******************************************************************************
 * @file    DAOPassages.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Database.DAO;

import java.sql.SQLException;
import java.sql.Statement;

import Database.DBConnection;

/**
 * @brief
 */
public class DAOPassages 
{
	/**
	 * @brief
	 */
	private String rowID = "ID";
	
	/**
	 * @brief
	 */
	private String rowSource = "Source";
	
	/**
	 * @brief
	 */
	private String rowDestination = "Destination";
	
	/**
	 * @brief
	 */
	private String rowDate = "Date";
	
	/**
	 * @brief
	 */
	private String rowQuantity = "Quantity";
	
	/**
	 * @brief
	 */
	private String rowPrice = "Price";
	
	/**
	 * @brief
	 */
	public DAOPassages()
	{
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * 
	 * @return
	 */
	public void createTable(Statement _stm) throws SQLException
	{
		_stm.execute("CREATE TABLE IF NOT EXISTS `"						+
					 DBConnection.getDatabaseName() 					+
					 "`.`" 												+
					 DBConnection.getPassagesDBName() 					+
					 "` ( `"											+
					 rowID												+
					 "` INT AUTO_INCREMENT PRIMARY KEY NOT NULL , `"	+
					 rowSource											+
					 "` TEXT NOT NULL , `"								+
					 rowDestination										+
					 "` TEXT NOT NULL , `"								+
					 rowDate											+
					 "` DATE NOT NULL , `"								+
					 rowQuantity										+
					 "` INT NOT NULL , `"								+
					 rowPrice											+
					 "` INT NOT NULL ) ENGINE = MyISAM;");
	}
}
