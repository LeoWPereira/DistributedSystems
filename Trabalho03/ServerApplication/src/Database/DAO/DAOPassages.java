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

import java.sql.Date;
import java.sql.ResultSet;
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
					 "` CHAR(100) NOT NULL  , `"						+
					 rowDestination										+
					 "` CHAR(100) NOT NULL  , `"						+
					 rowDate											+
					 "` DATE NOT NULL , `"								+
					 rowQuantity										+
					 "` INT NOT NULL , `"								+
					 rowPrice											+
					 "` FLOAT NOT NULL ) ENGINE = MyISAM;");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 */
	public void addUniqueValues(Statement _stm) throws SQLException
	{
		_stm.execute("ALTER TABLE `leonar14_DistributedSystems`.`Passages` ADD UNIQUE (`Source`, `Destination`, `Date`, `Price`) USING BTREE;");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 */
	public void dropTable(Statement _stm) throws SQLException
	{
		_stm.execute("DROP TABLE `"						+
					 DBConnection.getDatabaseName() 	+
					 "`.`" 								+
					 DBConnection.getPassagesDBName() 	+
					 "`;");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm		:
	 * @param	_source		:
	 * @param	_dest		:
	 * @param	_date		:
	 * @param	_quantity	:
	 * @param	_price		:
	 */
	public void insertEntry(Statement 	_stm,
							String 		_source,
							String 		_dest,
							Date		_date,
							int			_quantity,
							float		_price) throws SQLException
	{
		_stm.execute("INSERT INTO `"						+
					 DBConnection.getDatabaseName() 		+
					 "`.`" 									+
					 DBConnection.getPassagesDBName() 		+
					 "` (`" 								+ 
					 rowID 									+ 
					 "`, `" 								+ 
					 rowSource 								+ 
					 "`, `" 								+
					 rowDestination							+
					 "`, `"									+
					 rowDate								+
					 "`, `"									+
					 rowQuantity							+
					 "`, `"									+
					 rowPrice								+
					 "`) VALUES (NULL, '"					+
					 _source								+
					 "', '"									+
					 _dest									+
					 "', '"									+
					 _date									+
					 "', '"									+
					 _quantity								+
					 "', '"									+
					 _price									+
					 "');");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * @param	_source	:
	 * @param	_dest	:
	 */
	public void deleteEntry(Statement 	_stm,
							String 		_source,
							String 		_dest) throws SQLException
	{
		_stm.execute("DELETE FROM `"						+
					 DBConnection.getDatabaseName() 		+
					 "`.`" 									+
					 DBConnection.getPassagesDBName() 		+
					 "` WHERE `Source` = '"					+
					 _source 								+
					 "' AND `Destination` = '"				+
					 _dest									+
					 "';");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * 
	 * @return
	 */
	public ResultSet loadDBPassages(Statement	_stm) throws SQLException
	{
		ResultSet rs = _stm.executeQuery("SELECT * FROM `"					+
				 						 DBConnection.getDatabaseName()		+
			 						 	 "`.`" 								+
			 						 	 DBConnection.getPassagesDBName() 	+
				 						 "`");
		
		return rs;
	}
	
	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_source	:
	 * @param 	_dest	:
	 * @param 	_date	:
	 * 
	 * @return
	 */
	public ResultSet searchPassages(Statement	_stm,
									String 		_source,
									String 		_dest,
									Date		_date) throws SQLException
	{
		ResultSet rs = _stm.executeQuery("SELECT * FROM `"					+
										 DBConnection.getDatabaseName()		+
									 	 "`.`" 								+
									 	 DBConnection.getPassagesDBName() 	+
										 "` WHERE `"						+
									 	 rowSource							+
									 	 "` = '"							+
									 	 _source							+
									 	 "' AND `"							+
									 	 rowDestination						+
									 	 "` = '"							+
									 	 _dest								+
									 	 "' AND `"							+
									 	 rowDate							+
									 	 "` = '"							+
									 	 _date								+
									 	 "';");
		
		return rs;
	}
}
