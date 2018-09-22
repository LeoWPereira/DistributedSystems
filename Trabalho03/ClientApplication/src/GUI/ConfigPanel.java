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

public class ConfigPanel extends JPanel
{
	/**
	 * @brief
	 */
	private static final long serialVersionUID = -1876275958886425079L;

	/**
	 * @brief
	 * 
	 * @param	panel	-
	 */
	public ConfigPanel(JPanel panel)
	{
		panel.removeAll();
		
		panel.updateUI();
	}
}
