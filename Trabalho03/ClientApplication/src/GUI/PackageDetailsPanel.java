/**
 ******************************************************************************
 * @file    PackageDetailsPanel.java
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

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Classes.Accommodation;
import Extra.CitiesBrazil;
import RMI.ServerInterface;

/**
 * @brief
 */
public class PackageDetailsPanel extends JFrame
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
	private String hotelName;

	/**
	 * @brief
	 */
	private String citySrc;
	
	/**
	 * @brief
	 */
	private String stateSrc;

	/**
	 * @brief
	 */
	private String cityDest;
	
	/**
	 * @brief
	 */
	private String stateDest;

	/**
	 * @brief
	 */
	private Date goingDate;
	
	/**
	 * @brief
	 */
	private Date returnDate;
	
	
	/**
	 * @brief	Member containing the info about the "Reserve" label
	 */
	private static JLabel	labelReserve;
	
	/**
	 * @brief	Member containing the class of States and Cities from Brazil
	 */
	private static CitiesBrazil brazil;
	
	/**
	 * @brief	Member containing a Combo Box with the State Source
	 */
	private JComboBox<String> comboBoxStateSrc;
	
	/**
	 * @brief	Member containing a Combo Box with the City Source
	 */
	private JComboBox<String> comboBoxCitySrc;

	/**
	 * @brief	Member containing a Combo Box with the State Dest
	 */
	private JComboBox<String> comboBoxStateDest;
	
	/**
	 * @brief	Member containing a Combo Box with the City Dest
	 */
	private JComboBox<String> comboBoxCityDest;
	
	/**
	 * @brief	Member containing the reserve button
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
	private JLabel labelNumberOfGuests;
	
	/**
	 * @brief
	 */
	private JFormattedTextField	textNumberOfGuests;
	
	/**
	 * @brief
	 */
	private JFormattedTextField	textPrice;

	/**
	 * @brief
	 */
	private float	goingTicketPrice;

	/**
	 * @brief
	 */
	private float	returnTicketPrice;

	/**
	 * @brief
	 */
	private float	accommodationPrice;
	
	
	/**
	 * @brief
	 */
	public PackageDetailsPanel(ServerInterface	_server,
							   String 			_sourceState,
							   String			_sourceCity,
							   String			_destState,
							   String			_destCity,
							   Date			    _goingDate,
							   Date			    _returnDate,
							   String 			_hotelName,
							   float			_goingTicketPrice,
							   float			_returnTicketPrice,
							   float			_accommodationPrice) throws ParseException
	{
		contentPane = new JPanel();
		
		serverReference		= _server;
		hotelName 			= _hotelName;
		goingTicketPrice	= _goingTicketPrice;
		returnTicketPrice	= _returnTicketPrice;
		accommodationPrice	= _accommodationPrice;
		stateSrc 			= _sourceState;
	    citySrc				= _sourceCity;
	    stateDest			= _destState;
	    cityDest			= _destCity;
	    goingDate			= _goingDate;
	    returnDate			= _returnDate;
		
		setFrameSettings();
		
		configStateAndCities();
		
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
		
		setTitle("Detalhes do Pacote");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(200, 270,
				  400, 450);
		
		contentPane.setBorder(new EmptyBorder(5, 5,
				  							  5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JLabel labelReserve		= new JLabel("Comprar");
		JLabel labelHotelName	= new JLabel("Nome da Hospedagem: " + hotelName);

		// reserve label configurations
		labelReserve.setPreferredSize(new Dimension(75, 15));
		labelReserve.setBounds(160, 5,
						   	   100, 40);

		// hotel name label configurations
		labelHotelName.setPreferredSize(new Dimension(75, 15));
		labelHotelName.setBounds(10, 120,
						   		 300, 20);

		contentPane.add(labelReserve);
		contentPane.add(labelHotelName);
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
		comboBoxStateSrc.setSelectedItem(stateSrc);
		comboBoxStateSrc.setBounds(80, 60, 
								   60, 20);
		
		// Settings for the City Source comboBox
		comboBoxCitySrc.setEnabled(false);
		comboBoxCitySrc.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateSrc.getItemAt(comboBoxStateSrc.getSelectedIndex()))));
		comboBoxCitySrc.setSelectedItem(citySrc);
		comboBoxCitySrc.setBounds(160, 60, 
				                  180, 20);
		
		// Settings for the State Destination comboBox
		comboBoxStateDest.setEnabled(false);
		comboBoxStateDest.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxStateDest.setSelectedItem(stateDest);
		comboBoxStateDest.setBounds(80, 100, 
						   		    60, 20);
		
		// Settings for the City Destination comboBox
		comboBoxCityDest.setEnabled(false);
		comboBoxCityDest.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateDest.getItemAt(comboBoxStateDest.getSelectedIndex()))));
		comboBoxCityDest.setSelectedItem(cityDest);
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
		JLabel 		labelGoingDate	= new JLabel("Data Ida: ");
		
		JTextField	textGoingDate	= new JTextField();
		
		// Going Label date configurations
		labelGoingDate.setPreferredSize(new Dimension(75, 15));
		labelGoingDate.setBounds(10, 140,
						    120, 20);
		
		// Going Date textfield configurations
		textGoingDate.setEnabled(false);
		textGoingDate.setText(goingDate.toString());
		textGoingDate.setBounds(80, 140, 
						   80, 20);
		
		contentPane.add(labelGoingDate);
		contentPane.add(textGoingDate);

		if(returnTicketPrice != 0)
		{
			JLabel 		labelReturnDate	= new JLabel("Data Retorno: ");
		
			JTextField	textReturnDate	= new JTextField();
			
			// Going Label date configurations
			labelReturnDate.setPreferredSize(new Dimension(75, 15));
			labelReturnDate.setBounds(10, 140,
							    120, 20);
			
			// Going Date textfield configurations
			textReturnDate.setEnabled(false);
			textReturnDate.setText(returnDate.toString());
			textReturnDate.setBounds(80, 140, 
							   80, 20);
			
			contentPane.add(labelReturnDate);
			contentPane.add(textReturnDate);
		}
	}
	
	/**
	 * @brief
	 */
	public void configPrice() throws ParseException
	{
		JLabel 				labelPrice	= new JLabel("Preço Total (R$):");
		
		MaskFormatter 		mask		= new MaskFormatter("R$ ###.##");

		textPrice						= new JFormattedTextField(mask);
		
		// Label for One Way trip configurations
		labelPrice.setPreferredSize(new Dimension(75, 15));
		labelPrice.setBounds(10, 90,
						     120, 20);

		// Price textfield configurations
		textPrice.setEnabled(false);
		textPrice.setText(String.valueOf(goingTicketPrice + returnTicketPrice +	accommodationPrice));		
		textPrice.setBounds(100, 90, 
						    80, 20);
		
		contentPane.add(labelPrice);
		contentPane.add(textPrice);
	}
	
	/**
	 * @brief
	 */
	public void configParam() throws ParseException
	{
		labelQtd 			= new JLabel("Quantidade:");
		labelNumberOfGuests	= new JLabel("No. de pessoas:");
		
		MaskFormatter	maskQtd					= new MaskFormatter("###");
		MaskFormatter	maskNumberOfGuests		= new MaskFormatter("###");
		
		maskQtd.setValidCharacters("0123456789");
		maskNumberOfGuests.setValidCharacters("0123456789");
		
		textQtd				= new JFormattedTextField(maskQtd);
		textNumberOfGuests	= new JFormattedTextField(maskNumberOfGuests);
		
		// Label for Quantity configurations
		labelQtd.setPreferredSize(new Dimension(75, 15));
		labelQtd.setBounds(80, 160,
						   160, 20);

		// Label for Number of guests configurations
		labelNumberOfGuests.setPreferredSize(new Dimension(75, 15));
		labelNumberOfGuests.setBounds(80, 190,
						   			  160, 20);
		
		// textQtd settings
		textQtd.setPreferredSize(new Dimension(75, 25));
		textQtd.setBounds(200, 160,
						  80, 20);

		// textNumberOfGuests settings
		textNumberOfGuests.setPreferredSize(new Dimension(75, 25));
		textNumberOfGuests.setBounds(200, 190,
						  			 80, 20);
		
		labelQtd.setVisible(true);
		textQtd.setVisible(true);
		labelNumberOfGuests.setVisible(true);
		textNumberOfGuests.setVisible(true);
		
		contentPane.add(labelQtd);
		contentPane.add(textQtd);
		contentPane.add(labelNumberOfGuests);
		contentPane.add(textNumberOfGuests);
	}
	
	/**
	 * @brief
	 */
	public void configButton()
	{
		buttonBuy 		= new JButton("Comprar pacote");
		
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
					//processBuy();
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

		if(textNumberOfGuests.getText().toString().equals("   "))
		{
			returnValue = true;
		}
		
		return returnValue;
	}
}
