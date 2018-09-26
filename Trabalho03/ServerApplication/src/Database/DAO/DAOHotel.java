/**
 ******************************************************************************
 * @file    DAOHotel.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Database.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.DBConnection;

/**
 * @brief
 */
public class DAOHotel
{
	/**
	 * @brief
	 */
	private String rowID = "ID";
	
	/**
	 * @brief
	 */
	private String rowCity = "City";
	
	/**
	 * @brief
	 */
	private String rowHotel = "Hotel";
	
	/**
	 * @brief
	 */
	private String rowQuantity = "Quantity";
	
	/**
	 * @brief
	 */
	private String rowMaxGuestPerRoom = "MaxGuestPerRoom";
	
	/**
	 * @brief
	 */
	private String rowPrice = "Price";
	
	/**
	 * @brief
	 */
	public DAOHotel()
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
					 DBConnection.getHotelDBName()	 					+
					 "` ( `"											+
					 rowID												+
					 "` INT AUTO_INCREMENT PRIMARY KEY NOT NULL , `"	+
					 rowCity											+
					 "` CHAR(100) NOT NULL  , `"						+
					 rowHotel											+
					 "` CHAR(100) NOT NULL  , `"						+
					 rowQuantity										+
					 "` INT NOT NULL , `"								+
					 rowMaxGuestPerRoom									+
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
		_stm.execute("ALTER TABLE `"				+
					 DBConnection.getDatabaseName()	+
					 "`.`"							+
					 DBConnection.getHotelDBName()	+
					 "` ADD UNIQUE (`"				+
					 rowCity						+
					 "`, `"							+
					 rowHotel						+
					 "`) USING BTREE;");
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
					 DBConnection.getHotelDBName() 		+
					 "`;");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm				:
	 * @param	_city				:
	 * @param	_hotel				:
	 * @param	_quantity			:
	 * @param	_maxGuestsPerRoom	:
	 * @param	_price				:
	 */
	public void insertEntry(Statement 	_stm,
							String 		_city,
							String 		_hotel,
							int			_quantity,
							int			_maxGuestsPerRoom,
							float		_price) throws SQLException
	{
		_stm.execute("INSERT INTO `"						+
					 DBConnection.getDatabaseName() 		+
					 "`.`" 									+
					 DBConnection.getHotelDBName() 			+
					 "` (`" 								+ 
					 rowID 									+ 
					 "`, `" 								+ 
					 rowCity 								+ 
					 "`, `" 								+
					 rowHotel								+
					 "`, `"									+
					 rowQuantity							+
					 "`, `"									+
					 rowMaxGuestPerRoom						+
					 "`, `"									+
					 rowPrice								+
					 "`) VALUES (NULL, '"					+
					 _city									+
					 "', '"									+
					 _hotel									+
					 "', '"									+
					 _quantity								+
					 "', '"									+
					 _maxGuestsPerRoom						+
					 "', '"									+
					 _price									+
					 "');");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * @param	_city	:
	 * @param	_hotel	:
	 */
	public void deleteEntry(Statement 	_stm,
							String 		_city,
							String 		_hotel) throws SQLException
	{
		_stm.execute("DELETE FROM `"						+
					 DBConnection.getDatabaseName() 		+
					 "`.`" 									+
					 DBConnection.getHotelDBName() 			+
					 "` WHERE `"							+
					 rowCity								+
					 "` = '"								+
					 _city 									+
					 "' AND `"								+
					 rowHotel								+
					 "` = '"								+
					 _hotel									+
					 "';");
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * 
	 * @return
	 */
	public ResultSet loadDBHotels(Statement	_stm) throws SQLException
	{
		ResultSet rs = _stm.executeQuery("SELECT * FROM `"					+
				 						 DBConnection.getDatabaseName()		+
			 						 	 "`.`" 								+
			 						 	 DBConnection.getHotelDBName() 		+
				 						 "`");
		
		return rs;
	}
	
	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_city	:
	 * @param 	_hotel	:
	 * 
	 * @return
	 */
	public ResultSet searchPassages(Statement	_stm,
								    String 		_city,
								    String 		_hotel) throws SQLException
	{
		ResultSet rs = _stm.executeQuery("SELECT * FROM `"					+
										 DBConnection.getDatabaseName()		+
									 	 "`.`" 								+
									 	 DBConnection.getHotelDBName()	 	+
										 "` WHERE `"						+
									 	 rowCity							+
									 	 "` = '"							+
									 	 _city								+
									 	 "' AND `"							+
									 	 rowHotel							+
									 	 "` = '"							+
									 	 _hotel								+
									 	 "';");
		
		return rs;
	}
}
