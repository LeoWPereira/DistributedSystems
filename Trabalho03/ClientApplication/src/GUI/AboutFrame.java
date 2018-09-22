/**
 ******************************************************************************
 * @file    AboutFrame.java
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

public class AboutFrame extends JFrame
{
	/**
	 * @brief
	 */
	private static final long serialVersionUID = 4914872231876093191L;
	
	/**
	 * @brief
	 */
	JPanel contentPane;
	
	public AboutFrame()
	{
		contentPane = new JPanel();
		
		setFrameSettings();
		
		contentPane.setBorder(new EmptyBorder(5, 5,
											  5, 5));
		
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
	}
	
	/**
	 * @brief
	 */
	public void setFrameSettings()
	{
		setResizable(false);
		
		setTitle("Cliente - Sobre");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(300, 270,
				  350, 300);
	}
}
