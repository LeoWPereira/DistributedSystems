/**
 ******************************************************************************
 * @file    EventsPanel.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package GUI;

import javax.swing.JPanel;

public class EventsPanel extends JPanel
{
	/**
	 * @brief
	 */
	private static final long serialVersionUID = 1025943140078291898L;

	/**
	 * @brief
	 * 
	 * @param	panel	-
	 */
	public EventsPanel(JPanel panel)
	{
		panel.removeAll();
		
		panel.updateUI();
	}
}
