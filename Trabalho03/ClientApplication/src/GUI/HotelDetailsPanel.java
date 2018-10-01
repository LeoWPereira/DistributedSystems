/**
 ******************************************************************************
 * @file    HotelDetailsPanel.java
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
public class HotelDetailsPanel extends JFrame
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
	private String city;
	
	/**
	 * @brief
	 */
	private String state;
	
	/**
	 * @brief
	 */
	private float price;
	
	/**
	 * @brief	Member containing the info about the "Reserve" label
	 */
	private static JLabel	labelReserve;
	
	/**
	 * @brief	Member containing the class of States and Cities from Brazil
	 */
	private static CitiesBrazil brazil;
	
	/**
	 * @brief	Member containing a Combo Box with the State
	 */
	private JComboBox<String> comboBoxState;
	
	/**
	 * @brief	Member containing a Combo Box with the City
	 */
	private JComboBox<String> comboBoxCity;
	
	/**
	 * @brief	Member containing the reserve button
	 */
	private JButton buttonReserve;
	
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
	public HotelDetailsPanel(ServerInterface	_server,
							 String 			_name,
			 				 String 			_state,
							 String				_city,
							 float				_price) throws ParseException
	{
		contentPane = new JPanel();
		
		serverReference	= _server;

		hotelName 		= _name;
		
		city				= _city;
		state				= _state;
		price				= _price;
		
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
		
		setTitle("Detalhes da Reserva");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(200, 270,
				  400, 300);
		
		contentPane.setBorder(new EmptyBorder(5, 5,
				  							  5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JLabel labelReserve		= new JLabel("Reservar");
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
		
		comboBoxState 			= new JComboBox<String>();
		comboBoxCity			= new JComboBox<String>();
		
		JLabel labelPlace		= new JLabel("Local: ");
		
		// Source label configurations
		labelPlace.setPreferredSize(new Dimension(75, 15));
		labelPlace.setBounds(10, 60,
						   85, 20);
		
		// Settings for the State comboBox
		comboBoxState.setEnabled(false);
		comboBoxState.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxState.setSelectedItem(state);
		comboBoxState.setBounds(80, 60, 
								   60, 20);
		
		// Settings for the City comboBox
		comboBoxCity.setEnabled(false);
		comboBoxCity.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxState.getItemAt(comboBoxState.getSelectedIndex()))));
		comboBoxCity.setSelectedItem(city);
		comboBoxCity.setBounds(160, 60, 
				                  180, 20);
		
		contentPane.add(labelPlace);
		contentPane.add(comboBoxState);
		contentPane.add(comboBoxCity);
	}
	
	/**
	 * @brief
	 */
	public void configPrice() throws ParseException
	{
		JLabel 				labelPrice	= new JLabel("Preço (R$):");
		
		MaskFormatter 		mask		= new MaskFormatter("R$ ###.##");

		textPrice						= new JFormattedTextField(mask);
		
		// Label for One Way trip configurations
		labelPrice.setPreferredSize(new Dimension(75, 15));
		labelPrice.setBounds(10, 90,
						     120, 20);

		// Price textfield configurations
		textPrice.setEnabled(false);
		textPrice.setText(String.valueOf(price));
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
		buttonReserve 		= new JButton("Reservar hospedagem");
		
		// Settings for the Buy Button
		buttonReserve.setBorder(new BevelBorder(BevelBorder.RAISED, 
											null, 
											null, 
											null, 
											null));
		
		buttonReserve.setBackground(new Color(238, 238, 238));
		buttonReserve.setBounds(10, 235,
							380, 30);
		
		buttonReserve.addActionListener(new ActionListener()
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
					processReserve();
				}
			}
		});

		buttonReserve.setVisible(true);
		
		contentPane.add(buttonReserve);
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
	
	/**
	 * @brief
	 */
	public void processReserve()
	{
		Accommodation _hotel = new Accommodation(city,
												hotelName,
												Integer.valueOf(textQtd.getText().toString()),
												Integer.valueOf(textNumberOfGuests.getText().toString()),
												price);
		
		try 
		{
			if(serverReference.reserveHotel(_hotel))
			{
				JOptionPane.showMessageDialog(new JFrame(),
											  "A reserva foi realizada com sucesso!", 
											  "Sucesso",
											  JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(),
											  "Não há quartos suficientes!", 
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
