/**
 ******************************************************************************
 * @file    ConfigPanel.java
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
 * @brief	This Class will Handle every method from GUI "Configurations"
 */
public class ConfigPanel extends JPanel
{
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -1876275958886425079L;

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
	public ConfigPanel(JPanel panel)
	{
		internalPanel = panel;
		
		internalPanel.removeAll();
		
		internalPanel.updateUI();
		
		panel.removeAll();
		
		panel.updateUI();
	}
}
