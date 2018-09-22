/**
 ******************************************************************************
 * @file    PackagesPanel.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package GUI;

import javax.swing.JPanel;

public class PackagesPanel extends JPanel
{
	/**
	 * @brief
	 */
	private static final long serialVersionUID = -5995749487774459953L;

	/**
	 * @brief
	 * 
	 * @param	panel	-
	 */
	public PackagesPanel(JPanel panel)
	{
		panel.removeAll();
		
		panel.updateUI();
	}
}
