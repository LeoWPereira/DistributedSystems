/**
 ******************************************************************************
 * @file    HelpFrame.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @brief	This Class will Handle every method from GUI "Help"
 */
public class HelpFrame  extends JFrame
{	
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -3316704480403580052L;
	
	/**
	 * @brief	Member to store every GUI information on the current Panel
	 */
	JPanel contentPane;
	
	public HelpFrame()
	{
		contentPane = new JPanel();
		
		setFrameSettings();
		
		contentPane.setBorder(new EmptyBorder(5, 5,
											  5, 5));
		
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
	}
	
	/**
	 * @brief	Configure the settings of the Frame
	 */
	public void setFrameSettings()
	{
		setResizable(false);
		
		setTitle("Cliente - Ajuda");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(300, 270,
				  350, 300);
	}
}
