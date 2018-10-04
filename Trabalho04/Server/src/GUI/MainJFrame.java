package GUI;

import client.TravelAgencyService;
import client.TravelAgencyServiceImplService;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class MainJFrame extends JFrame
{
	/**
	 * @brief	Unique ID of the class
	 */
	private static final long serialVersionUID = -5197122419183947274L;

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
	private static TravelAgencyServiceImplService travelAgencyWebServiceImpl;
	
	/**
	 * @brief
	 */
	private static TravelAgencyService travelAgencyWebService;
	
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
					initWebServiceConnection();

					MainJFrame frame = new MainJFrame();
					
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
	public MainJFrame() throws ParseException
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
		
		setTitle("Servidor");
		
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
					Runtime.getRuntime().exec("hh.exe documentation/html/doc.chm");
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
	public void createInternalMenu() throws ParseException
	{
		JPanel panel 				= new JPanel();
		
		JButton buttonPassages		= new JButton("");
		JButton buttonHotel 		= new JButton("");
		JButton buttonConfigs	 	= new JButton("");
		
		JLabel labelPassages 		= new JLabel("Passagens");
		JLabel labelHotel 			= new JLabel("Hospedagem");
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
				PassagesPanel passagesPanel;
				
				try 
				{
					passagesPanel = new PassagesPanel(internalPanel, 
													  travelAgencyWebService);
					
					passagesPanel.setVisible(true);
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
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
				HotelsPanel hotelsPanel;
				
				try 
				{
					hotelsPanel = new HotelsPanel(internalPanel, 
												  travelAgencyWebService);
					
					hotelsPanel.setVisible(true);
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});

		// Configuration button configurations
		buttonConfigs.setBounds(240, 5,
				 				64, 64);
		
		buttonConfigs.setIcon(new ImageIcon(MainJFrame.class.getResource("/Images/settings.png")));
		buttonConfigs.setBackground(new Color(222, 184, 135));
		buttonConfigs.setBorder(null);
		
		buttonConfigs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//ConfigPanel configPanel = new ConfigPanel(internalPanel);
				
				//configPanel.setVisible(true);
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
		
		// Config label configurations
		labelConfigs.setPreferredSize(new Dimension(80, 15));
		labelConfigs.setBounds(235, 73,
			    			   102, 15);
				
		contentPane.add(panel);
		
		panel.add(buttonPassages);
		panel.add(buttonHotel);
		panel.add(buttonConfigs);
		
		panel.add(labelPassages);
		panel.add(labelHotel);
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
	 * @brief
	 */
	public static void initWebServiceConnection()
	{
		travelAgencyWebServiceImpl 	= new TravelAgencyServiceImplService();
   
		travelAgencyWebService 		= travelAgencyWebServiceImpl.getTravelAgencyServiceImplPort();
	}
}