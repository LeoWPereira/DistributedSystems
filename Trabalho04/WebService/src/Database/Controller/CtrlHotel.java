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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Database.DAO.DAOHotel;

/**
 * @brief
 */
public class CtrlHotel
{
	/**
	 * @brief
	 */
	private DAOHotel daoHotel;
	
	/**
	 * @brief
	 */
	public CtrlHotel()
	{
		daoHotel = new DAOHotel();
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 */
	public void createTable(Statement	_stm) throws SQLException
	{
		daoHotel.createTable(_stm);
		
		daoHotel.addUniqueValues(_stm);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 */
	public void dropTable(Statement _stm) throws SQLException
	{
		daoHotel.dropTable(_stm);
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
		daoHotel.insertEntry(_stm,
							 _city,
							 _hotel,
							 _quantity,
							 _maxGuestsPerRoom,
							 _price);
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
		daoHotel.deleteEntry(_stm,
							_city,
							_hotel);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_stm	:
	 * 
	 * @return
	 */
	public AccommodationManager loadDBHotels(Statement	_stm) throws SQLException
	{
		AccommodationManager list = new AccommodationManager();
		
		ResultSet rs = daoHotel.loadDBHotels(_stm);

		while(rs.next())
		{
			Accommodation value = new Accommodation(rs.getString(2),
												    rs.getString(3),
												    rs.getInt(4),
												    rs.getInt(5),
												    rs.getFloat(6));
			
			list.insertAccommodation(value);
		}
		
		return list;
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
	public AccommodationManager searchHotel(Statement	_stm,
											String 		_city,
											String 		_hotel) throws SQLException
	{
		AccommodationManager list = new AccommodationManager();
		
		ResultSet rs = daoHotel.searchHotel(_stm, 
											_city, 
											_hotel);

		while(rs.next())
		{
			Accommodation value = new Accommodation(rs.getString(2),
												    rs.getString(3),
												    rs.getInt(4),
												    rs.getInt(5),
												    rs.getFloat(6));
			
			list.insertAccommodation(value);
		}
		
		return list;
	}
	
	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_city	:
	 * 
	 * @return
	 */
	public AccommodationManager searchHotelByCity(Statement	_stm,
												  String 	_city) throws SQLException
	{
		AccommodationManager list = new AccommodationManager();
		
		ResultSet rs = daoHotel.searchHotelByCity(_stm, 
												  _city);

		while(rs.next())
		{
			Accommodation value = new Accommodation(rs.getString(2),
												    rs.getString(3),
												    rs.getInt(4),
												    rs.getInt(5),
												    rs.getFloat(6));
			
			list.insertAccommodation(value);
		}
		
		return list;
	}
	
	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_hotel	:
	 * 
	 * @return
	 */
	public AccommodationManager searchHotelByHotel(Statement	_stm,
												   String 		_hotel) throws SQLException
	{
		AccommodationManager list = new AccommodationManager();
		
		ResultSet rs = daoHotel.searchHotelByHotel(_stm, 
												   _hotel);

		while(rs.next())
		{
			Accommodation value = new Accommodation(rs.getString(2),
												    rs.getString(3),
												    rs.getInt(4),
												    rs.getInt(5),
												    rs.getFloat(6));
			
			list.insertAccommodation(value);
		}
		
		return list;
	}

	/**
	 * @brief
	 * 
	 * @param 	_stm	:
	 * @param 	_city	:
	 * @param 	_hotel	:
	 * @param	_price	:
	 * 
	 * @return
	 */
	public int getQuantityLeft(Statement	_stm,
							   String 		_city,
							   String 		_hotel,
							   float		_price) throws SQLException
	{
		int left = 0;
		
		ResultSet rs = daoHotel.getQuantityLeft(_stm,
												_city,
												_hotel,
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
	 * @param 	_city	:
	 * @param 	_hotel	:
	 * @param	_price	:
	 * @param	_newQtd	:
	 * 
	 * @return
	 */
	public void updateQuantity(Statement	_stm,
							   String 		_city,
							   String 		_hotel,
							   float  		_price,
							   int			_newQtd) throws SQLException
	{
		daoHotel.updateQuantity(_stm,
								_city,
								_hotel,
								_price,
								_newQtd);
	}
}
