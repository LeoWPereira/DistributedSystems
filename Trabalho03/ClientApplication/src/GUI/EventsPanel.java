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

/**
 * @brief	This Class will Handle every method from GUI "Events"
 */
public class EventsPanel extends JPanel
{
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 1025943140078291898L;

	/**
	 * @brief	Member to store every GUI information on the current Panel
	 */
	private static JPanel 		internalPanel;
	
	/**
	 * @brief	Default Constructor
	 * 
	 * This constructor will first remove everything from the JPanel
	 * 
	 * @param	panel	-	JPanel containing this panel future info
	 */
	public EventsPanel(JPanel panel)
	{
		internalPanel = panel;
		
		internalPanel.removeAll();
		
		internalPanel.updateUI();
	}
}
