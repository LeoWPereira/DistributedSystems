/**
 ******************************************************************************
 * @file    HotelsPanel.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package GUI;

import javax.swing.JPanel;

public class HotelsPanel extends JPanel
{
	/**
	 * @brief
	 */
	private static final long serialVersionUID = 7468938695192643425L;

	/**
	 * @brief
	 * 
	 * @param	panel	-
	 */
	public HotelsPanel(JPanel panel)
	{
		panel.removeAll();
		
		panel.updateUI();
	}
}
