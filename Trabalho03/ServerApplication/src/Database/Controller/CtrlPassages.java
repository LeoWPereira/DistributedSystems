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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.FlightTicket;
import Classes.FlightTicketManager;
import Database.DAO.DAOPassages;

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
		FlightTicketManager list = new FlightTicketManager();
		
		ResultSet rs = daoPassages.loadDBPassages(_stm);

		while(rs.next())
		{
			FlightTicket ticket = new FlightTicket(rs.getString(2),
												   rs.getString(3),
												   rs.getDate(4),
												   rs.getInt(5),
												   rs.getFloat(6));
			
			list.insertFlightTicket(ticket);
		}
		
		return list;
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
	public FlightTicketManager searchPassages(Statement	_stm,
											  String 	_source,
											  String 	_dest,
											  Date		_date) throws SQLException
	{
		FlightTicketManager list = new FlightTicketManager();
		
		ResultSet rs = daoPassages.searchPassages(_stm,
												  _source,
												  _dest,
												  _date);

		while(rs.next())
		{
			FlightTicket ticket = new FlightTicket(rs.getString(2),
												   rs.getString(3),
												   rs.getDate(4),
												   rs.getInt(5),
												   rs.getFloat(6));
			
			list.insertFlightTicket(ticket);
		}
		
		return list;
	}
	
	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_source	:
	 * @param 	_dest	:
	 * @param 	_date	:
	 * @param	_price	:
	 * 
	 * @return
	 */
	public int getQuantityLeft(Statement	_stm,
							   String 		_source,
							   String 		_dest,
							   Date			_date,
							   float		_price) throws SQLException
	{
		int left = 0;
		
		ResultSet rs = daoPassages.getQuantityLeft(_stm,
												   _source,
												   _dest,
												   _date,
												   _price);
		
		if(rs.next())
		{
			left = rs.getInt(1);
		}
		
		return left;
	}
	
	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_source	:
	 * @param 	_dest	:
	 * @param 	_date	:
	 * @param	_price	:
	 *  @param	_newQtd	:
	 * 
	 * @return
	 */
	public void updateQuantity(Statement	_stm,
							   String 		_source,
							   String 		_dest,
							   Date			_date,
							   float		_price,
							   int			_newQtd) throws SQLException
	{
		daoPassages.updateQuantity(_stm,
								   _source,
								   _dest,
								   _date,
								   _price,
								   _newQtd);
	}
}
