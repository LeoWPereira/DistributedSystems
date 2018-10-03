/**
 ******************************************************************************
 * @file    PassagesDetailsPanel.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Classes.FlightTicket;
import Extra.CitiesBrazil;
import RMI.ServerInterface;

/**
 * @brief
 */
public class PassagesDetailsPanel extends JFrame
{

	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -7396970554206976421L;

	/**
	 * @brief	Member to store every GUI information on the current Panel
	 */
	private JPanel contentPane;
	
	/**
	 * @brief
	 */
	private static ServerInterface serverReference;
	
	/**
	 * @brief
	 */
	private String sourceCity;
	
	/**
	 * @brief
	 */
	private String sourceState;
	
	/**
	 * @brief
	 */
	private String destinationCity;
	
	/**
	 * @brief
	 */
	private String destinationState;
	
	/**
	 * @brief
	 */
	private Date date;
	
	/**
	 * @brief
	 */
	private float price;
	
	/**
	 * @brief	Member containing the class of States and Cities from Brazil
	 */
	private static CitiesBrazil brazil;
	
	/**
	 * @brief	Member containing a Combo Box with the Source State
	 */
	private JComboBox<String> comboBoxStateSrc;
	
	/**
	 * @brief	Member containing a Combo Box with the Source City
	 */
	private JComboBox<String> comboBoxCitySrc;
	
	/**
	 * @brief	Member containing a Combo Box with the Destination State
	 */
	private JComboBox<String> comboBoxStateDest;
	
	/**
	 * @brief	Member containing a Combo Box with the Destination City
	 */
	private JComboBox<String> comboBoxCityDest;
	
	/**
	 * @brief	Member containing the buy button
	 */
	private JButton buttonBuy;
	
	/**
	 * @brief
	 */
	private JLabel labelQtd;
	
	/**
	 * @brief
	 */
	private JFormattedTextField	textQtd;
	
	
	/**
	 * @brief
	 */
	public PassagesDetailsPanel(ServerInterface	_server,
			 					String 			_sourceSate,
								String			_sourceCity,
								String			_destState,
								String			_destCity,
								Date			_date,
								float			_price) throws ParseException
	{
		contentPane = new JPanel();
		
		serverReference	= _server;
		
		sourceCity			= _sourceCity;
		destinationCity		= _destCity;
		sourceState			= _sourceSate;
		destinationState	= _destState;
		date 				= _date;
		price				= _price;
		
		setFrameSettings();
		
		configStateAndCities();
		
		configDates();
		
		configPrice();
		
		configParam();
		
		configButton();
	}
	
	/**
	 * @brief	Configure the settings of the Frame
	 */
	public void setFrameSettings()
	{
		setResizable(false);
		
		setTitle("Detalhes da Passagem");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(200, 270,
				  400, 300);
		
		contentPane.setBorder(new EmptyBorder(5, 5,
				  							  5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
	}
	
	/**
	 * @brief	Initial settings for the several ComboBox of the Panel
	 */
	public void configStateAndCities()
	{
		brazil 					= new CitiesBrazil();
		
		comboBoxStateSrc 		= new JComboBox<String>();
		comboBoxCitySrc			= new JComboBox<String>();
		comboBoxStateDest		= new JComboBox<String>();
		comboBoxCityDest		= new JComboBox<String>();
		
		JLabel labelSrc			= new JLabel("Origem: ");
		JLabel labelDest		= new JLabel("Destino: ");
		
		// Source label configurations
		labelSrc.setPreferredSize(new Dimension(75, 15));
		labelSrc.setBounds(10, 60,
						   85, 20);
		
		// Destination Label configurations
		labelDest.setPreferredSize(new Dimension(80, 15));
		labelDest.setBounds(10, 100,
							85, 20);
		
		// Settings for the State Source comboBox
		comboBoxStateSrc.setEnabled(false);
		comboBoxStateSrc.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxStateSrc.setSelectedItem(sourceState);
		comboBoxStateSrc.setBounds(80, 60, 
								   60, 20);
		
		// Settings for the City Source comboBox
		comboBoxCitySrc.setEnabled(false);
		comboBoxCitySrc.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateSrc.getItemAt(comboBoxStateSrc.getSelectedIndex()))));
		comboBoxCitySrc.setSelectedItem(sourceCity);
		comboBoxCitySrc.setBounds(160, 60, 
				                  180, 20);
		
		// Settings for the State Destination comboBox
		comboBoxStateDest.setEnabled(false);
		comboBoxStateDest.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxStateDest.setSelectedItem(destinationState);
		comboBoxStateDest.setBounds(80, 100, 
						   		    60, 20);
		
		// Settings for the City Destination comboBox
		comboBoxCityDest.setEnabled(false);
		comboBoxCityDest.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateDest.getItemAt(comboBoxStateDest.getSelectedIndex()))));
		comboBoxCityDest.setSelectedItem(destinationCity);
		comboBoxCityDest.setBounds(160, 100, 
				                   180, 20);
		
		contentPane.add(labelSrc);
		contentPane.add(labelDest);
		contentPane.add(comboBoxStateSrc);
		contentPane.add(comboBoxCitySrc);
		contentPane.add(comboBoxStateDest);
		contentPane.add(comboBoxCityDest);
	}
	
	/**
	 * @brief	This method will handle the initial setup to the Dates Labels and Calendars
	 */
	public void configDates()
	{
		JLabel 		labelDate	= new JLabel("Data: ");
		
		JTextField	textDate	= new JTextField();
		
		// Label for One Way trip configurations
		labelDate.setPreferredSize(new Dimension(75, 15));
		labelDate.setBounds(10, 140,
						    120, 20);
		
		// Date textfield configurations
		textDate.setEnabled(false);
		
		textDate.setText(date.toString().substring(4, 7) + ' ' 		+ 
						 date.toString().substring(8, 10) + ", " 	+
						 date.toString().substring(24, 28));
		
		textDate.setBounds(80, 140, 
						   80, 20);
		
		contentPane.add(labelDate);
		contentPane.add(textDate);
	}
	
	/**
	 * @brief
	 */
	public void configPrice() throws ParseException
	{
		JLabel 				labelPrice	= new JLabel("Preço (R$):");
		
		MaskFormatter 		mask		= new MaskFormatter("R$ ###.##");
		
		JFormattedTextField	textDate	= new JFormattedTextField(mask);
		
		// Label for One Way trip configurations
		labelPrice.setPreferredSize(new Dimension(75, 15));
		labelPrice.setBounds(180, 140,
						     120, 20);
		
		// Date textfield configurations
		textDate.setEnabled(false);
		textDate.setText(String.valueOf(price));
		textDate.setBounds(260, 140, 
						   80, 20);
		
		contentPane.add(labelPrice);
		contentPane.add(textDate);
	}
	
	/**
	 * @brief
	 */
	public void configParam() throws ParseException
	{
		labelQtd 			= new JLabel("Quantidade:");
		
		MaskFormatter	maskQtd		= new MaskFormatter("###");
		
		maskQtd.setValidCharacters("0123456789");
		
		textQtd				= new JFormattedTextField(maskQtd);
		
		// Label for One Way trip configurations
		labelQtd.setPreferredSize(new Dimension(75, 15));
		labelQtd.setBounds(80, 190,
						   160, 20);
		
		// textQtd settings
		textQtd.setPreferredSize(new Dimension(75, 25));
		textQtd.setBounds(200, 190,
						  80, 20);
		
		labelQtd.setVisible(true);
		textQtd.setVisible(true);
		
		contentPane.add(labelQtd);
		contentPane.add(textQtd);
	}
	
	/**
	 * @brief
	 */
	public void configButton()
	{
		buttonBuy 		= new JButton("Comprar Passagem");
		
		// Settings for the Buy Button
		buttonBuy.setBorder(new BevelBorder(BevelBorder.RAISED, 
											null, 
											null, 
											null, 
											null));
		
		buttonBuy.setBackground(new Color(238, 238, 238));
		buttonBuy.setBounds(10, 235,
							380, 30);
		
		buttonBuy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(checkForEmptyFields())
				{
					JOptionPane.showMessageDialog(new JFrame(),
												  "Existem Campos não preenchidos!", 
												  "Erro",
												  JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					processBuy();
				}
			}
		});

		buttonBuy.setVisible(true);
		
		contentPane.add(buttonBuy);
	}
	
	/**
	 * @brief	Check whether or not there is an (or more) empty field(s) to be filled
	 * 
	 * @return	True if there is at least one empty field
	 * 			False otherwise
	 */
	public boolean checkForEmptyFields()
	{
		boolean returnValue = false;

		if(textQtd.getText().toString().equals("   "))
		{
			returnValue = true;
		}
		
		return returnValue;
	}
	
	/**
	 * @brief
	 */
	public void processBuy()
	{
		FlightTicket _ticket = new FlightTicket(sourceCity,
												destinationCity,
												date,
												Integer.valueOf(textQtd.getText().toString()),
												price);
		
		try 
		{
			if(serverReference.buyPassage(_ticket))
			{
				JOptionPane.showMessageDialog(new JFrame(),
											  "A compra foi realizada com sucesso!", 
											  "Sucesso",
											  JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(),
											  "Quantidade Restante de Passagens insuficiente!", 
											  "Erro",
											  JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}
}
