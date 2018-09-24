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

import java.sql.SQLException;
import java.sql.Statement;

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
	 * 
	 * @return
	 */
	public void createTable(Statement	_stm) throws SQLException
	{
		daoPassages.createTable(_stm);
	}
}
