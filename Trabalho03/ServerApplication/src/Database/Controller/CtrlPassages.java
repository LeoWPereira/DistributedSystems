/**
 ******************************************************************************
 * @file    CtrlPassages.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Database.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.FlightTicketManager;
import Database.DAO.DAOPassages;

/**
 * @brief
 */
public class CtrlPassages
{
	/**
	 * @brief
	 */
	private DAOPassages daoPassages;
	
	/**
	 * @brief
	 */
	public CtrlPassages()
	{
		daoPassages = new DAOPassages();
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 */
	public void createTable(Statement	_stm) throws SQLException
	{
		daoPassages.createTable(_stm);
		
		daoPassages.addUniqueValues(_stm);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 */
	public void dropTable(Statement _stm) throws SQLException
	{
		daoPassages.dropTable(_stm);
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
		daoPassages.insertEntry(_stm,
								_source,
								_dest,
								_date,
								_quantity,
								_price);
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
		daoPassages.deleteEntry(_stm,
								_source,
								_dest);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * 
	 * @return
	 */
	public FlightTicketManager loadDBPassages(Statement	_stm) throws SQLException
	{
		return daoPassages.loadDBPassages(_stm);
	}
}
