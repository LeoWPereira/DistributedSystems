/**
 ******************************************************************************
 * @file    MainJFrame.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import RMI.ClientServent;
import RMI.ServerInterface;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * @brief	The class will handle the main JFrame of the entire application
 * 
 * This class will not only deal with calls of Panel constructors, but also
 * connect with the database and manage calls to the RMI middleware
 */
public class MainJFrame extends JFrame
{
	/**
	 * @brief	Unique ID of the class
	 */
	private static final long serialVersionUID = 1382167513878990127L;

	/**
	 * @brief	Panel containing every info about the internal menu
	 */
	private static JPanel contentPane;
	
	/**
	 * @brief	Member containing every info about the internal panel
	 */
	private static JPanel internalPanel;
	
	/**
	 * @brief
	 */
	private static ClientServent clientRMI;
	
	/**
	 * @brief
	 */
	private static ServerInterface serverReference;
	
	/**
	 * @brief	Main method of the application
	 * 
	 * This is the app start point
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{					
					MainJFrame frame = new MainJFrame();

					initRMI();
					
					frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @brief	Create the Frame and defines its initial settings
	 */
	public MainJFrame()
	{
		contentPane = new JPanel();
		
		setFrameSettings();
		
		createMenu();
		
		createInternalMenu();
		
		createInternalPanel();
	}

	/**
	 * @brief	Main Frame initial settings
	 */
	public void setFrameSettings()
	{
		//setIconImage(Toolkit.getDefaultToolkit().getImage(MainJFrame.class.getResource("/Images/Logo Mr Limp.png")));
		
		setResizable(false);
		
		setTitle("Cliente");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100,
				  800, 600);
	}
	
	/**
	 * @brief	Create and configure the Main Menu (Upper Menu)
	 */
	public void createMenu()
	{
		JMenuBar menuBar		= new JMenuBar();
		
		JMenu menuFile	 		= new JMenu("Arquivo");
		JMenu menuHelp 			= new JMenu("Ajuda");
		
		JMenuItem subMenuExit	= new JMenuItem("Sair");
		JMenuItem subMenuHelp 	= new JMenuItem("Ajuda");
		JMenuItem subMenuAbout 	= new JMenuItem("Sobre");
		
		setJMenuBar(menuBar);
		
		// Menu Bar configurations
		menuBar.setBackground(new Color(0, 0, 0));
		
		// File menu configurations
		menuFile.setForeground(new Color(255, 255, 255));
		
		// Help menu configurations
		menuHelp.setForeground(new Color(255, 255, 255));
		
		// Sub menu exit configurations
		subMenuExit.setForeground(new Color(255, 255, 255));
		subMenuExit.setBackground(new Color(0, 0, 0));
		
		subMenuExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		
		// Sub menu Help configurations
		subMenuHelp.setBackground(new Color(0, 0, 0));
		subMenuHelp.setForeground(new Color(255, 255, 255));
		
		subMenuHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				 try 
				 {
					Runtime.getRuntime().exec("hh.exe doc.chm");
				 }
				 catch (IOException e) 
				 {
					e.printStackTrace();
				}
			}
		});
		
		//Sub menu About configurations
		subMenuAbout.setForeground(new Color(255, 255, 255));
		subMenuAbout.setBackground(new Color(0, 0, 0));
		
		subMenuAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AboutFrame aboutFrame = new AboutFrame();
				
				aboutFrame.setVisible(true);
			}
		});
		
		// FInally, just set the menu the way you like
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		
		menuFile.add(subMenuExit);
		
		menuHelp.add(subMenuHelp);
		menuHelp.add(subMenuAbout);
	}
	
	/**
	 * @brief	Create and configure the Internal Menu
	 */
	public void createInternalMenu()
	{
		JPanel panel 				= new JPanel();
		
		JButton buttonPassages		= new JButton("");
		JButton buttonHotel 		= new JButton("");
		JButton buttonPackages 		= new JButton("");
		JButton buttonEvents 		= new JButton("");
		JButton buttonConfigs	 	= new JButton("");
		
		JLabel labelPassages 		= new JLabel("Passagens");
		JLabel labelHotel 			= new JLabel("Hospedagem");
		JLabel labelPackages		= new JLabel("Pacotes");
		JLabel labelEvents 			= new JLabel("Eventos");
		JLabel labelConfigs 		= new JLabel("Configurações");
		
		setContentPane(contentPane);
		
		// internal menu configurations
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		// panel configurations
		panel.setBounds(5, 5,
						786, 100);
		
		panel.setPreferredSize(new Dimension(10, 100));
		panel.setBackground(new Color(222, 184, 135));
		panel.setLayout(null);
		
		// Passages button configurations
		buttonPassages.setBounds(12, 5,
								 64, 64);
		
		buttonPassages.setBorder(null);
		buttonPassages.setBackground(new Color(222, 184, 135));
		buttonPassages.setIcon(new ImageIcon(MainJFrame.class.getResource("/Images/plane-icon.png")));

		buttonPassages.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				PassagesPanel passagesPanel = new PassagesPanel(internalPanel,
																serverReference,
																clientRMI);
				
				passagesPanel.setVisible(true);
			}
		});
		
		// Hotel button configurations
		buttonHotel.setBounds(120, 5,
							  64, 64);
		
		buttonHotel.setIcon(new ImageIcon(MainJFrame.class.getResource("/Images/Cama_Real.png")));
		buttonHotel.setBorder(null);
		buttonHotel.setBackground(new Color(222, 184, 135));
		
		buttonHotel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				HotelsPanel hotelsPanel = new HotelsPanel(internalPanel,
														  serverReference,
														  clientRMI);
				
				hotelsPanel.setVisible(true);
			}
		});
				
		// Package button configurations
		buttonPackages.setBounds(228, 5,
								 64, 64);
		
		buttonPackages.setIcon(new ImageIcon(MainJFrame.class.getResource("/Images/todo.png")));
		buttonPackages.setBackground(new Color(222, 184, 135));
		buttonPackages.setBorder(null);
		
		buttonPackages.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				PackagesPanel packagesPanel = new PackagesPanel(internalPanel,
																serverReference,
																clientRMI);
				
				packagesPanel.setVisible(true);
			}
		});
		
		// Event button configurations
		buttonEvents.setBounds(336, 5,
							   64, 64);
		
		buttonEvents.setIcon(new ImageIcon(MainJFrame.class.getResource("/Images/booklet.png")));
		buttonEvents.setBackground(new Color(222, 184, 135));
		buttonEvents.setBorder(null);
		
		buttonEvents.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				EventsPanel eventsPanel = new EventsPanel(internalPanel,
														  clientRMI);
				
				eventsPanel.setVisible(true);
			}
		});
		
		// Configuration button configurations
		buttonConfigs.setBounds(444, 5,
								64, 64);
		
		buttonConfigs.setIcon(new ImageIcon(MainJFrame.class.getResource("/Images/settings.png")));
		buttonConfigs.setBackground(new Color(222, 184, 135));
		buttonConfigs.setBorder(null);
		
		buttonConfigs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfigPanel configPanel = new ConfigPanel(internalPanel);
				
				configPanel.setVisible(true);
			}
		});
		
		// Passages label configurations
		labelPassages.setPreferredSize(new Dimension(75, 15));
		labelPassages.setBounds(8, 73,
							    85, 15);
		
		// Hotel Label configurations
		labelHotel.setPreferredSize(new Dimension(80, 15));
		labelHotel.setBounds(115, 73,
							 90, 15);
		
		// Packages label configurations
		labelPackages.setPreferredSize(new Dimension(80, 15));
		labelPackages.setBounds(235, 73,
							    70, 15);
		
		// Events label configurations
		labelEvents.setPreferredSize(new Dimension(80, 15));
		labelEvents.setBounds(345, 73,
							  70, 15);
		
		// Config label configurations
		labelConfigs.setPreferredSize(new Dimension(80, 15));
		labelConfigs.setBounds(445, 73,
				   			   102, 15);
				
		contentPane.add(panel);
		
		panel.add(buttonPassages);
		panel.add(buttonHotel);
		panel.add(buttonPackages);
		panel.add(buttonEvents);
		panel.add(buttonConfigs);
		
		panel.add(labelPassages);
		panel.add(labelHotel);
		panel.add(labelPackages);
		panel.add(labelEvents);
		panel.add(labelConfigs);
	}
	
	/**
	 * @brief	Create and configure the Internal Panel
	 */
	public void createInternalPanel()
	{
		internalPanel = new JPanel();
		
		// panel configurations
		internalPanel.setBounds(5, 120,
						        782, 420);
		
		internalPanel.setPreferredSize(new Dimension(10, 100));
		internalPanel.setLayout(null);
				
		contentPane.add(internalPanel);
	}
	
	/**
	 * @brief	Locate server
	 */
	public static void initRMI()
	{
        try 
        {
        	Registry referenceServerName = LocateRegistry.getRegistry();
            
            serverReference = (ServerInterface)referenceServerName.lookup("Servidor");
            
            clientRMI = new ClientServent(serverReference, 
            							  JOptionPane.showInputDialog("Digite o seu nome: "));
		} 
        catch(AccessException e)
        {
			e.printStackTrace();
		} 
        catch (RemoteException e) 
        {
			e.printStackTrace();
		} 
        catch (NotBoundException e) 
        {
			e.printStackTrace();
		}
	}
}
