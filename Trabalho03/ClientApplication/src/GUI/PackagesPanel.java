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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Extra.CitiesBrazil;
import Classes.Package;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * @brief	This Class will Handle every method from GUI "Package"
 */
public class PackagesPanel extends JPanel
{
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -5995749487774459953L;

	/**
	 * @brief	Member to store every GUI information on the current Panel
	 */
	private static JPanel 		internalPanel;
	
	/**
	 * @brief	Member to store the group of Radio Buttons
	 */
	private static ButtonGroup 	group;
	
	/**
	 * @brief	Member containing the info about the "One-Way" Radio Button
	 */
	private static JRadioButton	radioButtonOneWay;
	
	/**
	 * @brief	Member containing the info about the "Round-Way" Radio Button
	 */
	private static JRadioButton	radioButtonRoundWay;
	
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
	 * @brief	Member containing a table where will be shown Passages Info
	 */
	private JTable 			  table;
	
	/**
	 * @brief	Member containing info about the table scroll
	 */
	private JScrollPane 	  scrollPaneTabela;
	
	/**
	 * @brief	Member containing the search button
	 */
	private JButton 		  buttonSearch;
	
	/**
	 * @brief	Member containing the label "Round Trip"
	 * 
	 * This label is not only local (as almost every other label in the application)
	 * because we need to set its visibility attribute once a while
	 */
	private JLabel 		  	  labelDateRoundTrip;
	
	/**
	 * @brief	Member containing a "Date Picker" for One Way trips
	 */
	private JDatePickerImpl   datePickerOneWayTrip;
	
	/**
	 * @brief	Member containing a "Date Picker" for Round Trip trips
	 */
	private JDatePickerImpl   datePickerRoundTrip;
	
	/**
	 * @brief	Member containing a "Specific Hotel" checkbox
	 */
	private JCheckBox		  checkBoxSpecificHotel;
	
	/**
	 * @brief	Member containing a label to "Hotel" info
	 */
	private JLabel 			  labelHotel;
	
	/**
	 * @brief	Member containing the hotel name text field
	 */
	private JTextField 		  textFieldHotel;
	
	/**
	 * @brief	Default Constructor
	 * 
	 * This constructor will first remove everything from the JPanel
	 * 
	 * @param	panel	-	JPanel containing this panel future info
	 */
	public PackagesPanel(JPanel panel)
	{
		internalPanel = panel;
		
		internalPanel.removeAll();
		
		configRadioButtons();
		
		configCheckBox();
		
		configStateAndCities();
		
		configDates();
		
		configHotelSearch();
		
		configButton();
		
		configTable();
		
		internalPanel.updateUI();
	}
	
	/**
	 * @brief	Initial settings for the radio buttons and radio buttons group
	 */
	public void configRadioButtons()
	{
		group 				= new ButtonGroup();
		
		radioButtonOneWay 	= new JRadioButton("Somente Ida");
		radioButtonRoundWay	= new JRadioButton("Ida / Volta");
		
		// Settings for the One Way Radio Button
		radioButtonOneWay.setSelected(true);
		radioButtonOneWay.setBounds(225, 5,
									100, 40);
		
		radioButtonOneWay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelDateRoundTrip.setVisible(false);
				
				datePickerRoundTrip.setVisible(false);
			}
		});
		
		// Settings for the Round Trip Radio Button
		radioButtonRoundWay.setBounds(425, 5,
									  100, 40);
		
		radioButtonRoundWay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelDateRoundTrip.setVisible(true);
				
				datePickerRoundTrip.setVisible(true);
			}
		});
		
        group.add(radioButtonOneWay);
        group.add(radioButtonRoundWay);
        
        internalPanel.add(radioButtonOneWay);
        internalPanel.add(radioButtonRoundWay);
	}
	
	/**
	 * @brief	Initial settings for the CheckBox of the Panel
	 */
	public void configCheckBox()
	{
		checkBoxSpecificHotel	= new JCheckBox("Especificar Hotel");
		
		// Settings for CheckBox
		checkBoxSpecificHotel.setPreferredSize(new Dimension(75, 15));
		checkBoxSpecificHotel.setBounds(310, 35,
									    180, 40);
		
		checkBoxSpecificHotel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelHotel.setVisible(!labelHotel.isVisible());
				
				textFieldHotel.setVisible(!textFieldHotel.isVisible());
			}
		});
				
		internalPanel.add(checkBoxSpecificHotel);
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
		labelSrc.setBounds(10, 80,
						   85, 20);
		
		// Destination Label configurations
		labelDest.setPreferredSize(new Dimension(80, 15));
		labelDest.setBounds(10, 130,
							85, 20);
		
		// Settings for the State Source comboBox
		comboBoxStateSrc.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxStateSrc.setBounds(80, 80, 
								   60, 20);
		
		comboBoxStateSrc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				changeCitiesComboBox(comboBoxCitySrc,
									 comboBoxStateSrc.getItemAt(comboBoxStateSrc.getSelectedIndex()));
			}
		});
		
		// Settings for the City Source comboBox
		comboBoxCitySrc.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateSrc.getItemAt(comboBoxStateSrc.getSelectedIndex()))));
		comboBoxCitySrc.setBounds(160, 80, 
				                  180, 20);
		
		// Settings for the State Destination comboBox
		comboBoxStateDest.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxStateDest.setBounds(80, 130, 
						   		    60, 20);
		
		comboBoxStateDest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				changeCitiesComboBox(comboBoxCityDest,
									 comboBoxStateDest.getItemAt(comboBoxStateDest.getSelectedIndex()));
			}
		});
		
		// Settings for the City Destination comboBox
		comboBoxCityDest.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateSrc.getItemAt(comboBoxStateSrc.getSelectedIndex()))));
		comboBoxCityDest.setBounds(160, 130, 
				                   180, 20);
		
		internalPanel.add(labelSrc);
		internalPanel.add(labelDest);
		internalPanel.add(comboBoxStateSrc);
		internalPanel.add(comboBoxCitySrc);
		internalPanel.add(comboBoxStateDest);
		internalPanel.add(comboBoxCityDest);
	}
	
	/**
	 * @brief	The method will handle changes if the State ComboBox
	 * 
	 * @param	comboBox	-	The current comboBox (Source of Destination)
	 * @param	index		-	The index of the comboBox to look to
	 */
	public void changeCitiesComboBox(JComboBox<String>	comboBox,
									 String				index)
	{
		comboBox.setModel(new DefaultComboBoxModel<String>(brazil.getCities(index)));
	}
	
	/**
	 * @brief	The method will handle the initial settings for the table
	 */
	@SuppressWarnings("serial")
	public void configTable()
	{
		table 							= new JTable();
		scrollPaneTabela 				= new JScrollPane();
		
		DefaultTableCellRenderer dtcr	= new DefaultTableCellRenderer();
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Settings for the Scroll
		scrollPaneTabela.setBackground(new Color(222, 184, 135));
		scrollPaneTabela.setBorder(null);
		scrollPaneTabela.setViewportView(table);
		scrollPaneTabela.setBounds(370, 80,
								   400, 321);
		
		// Settings for the Table
		table.setShowGrid(false);
		table.setBorder(null);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(new Color(222, 184, 135));
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null}
					},
			new String[] {"Origem", "Destino", "Hotel", "Pre�o Ida", "Pre�o Volta", "Pre�o Hospedagem" "Pre�o Total"})
			{
				boolean[] columnEditables = new boolean[]
				{
					false, false, false, false, false, false, false, false, false, false
				};
				
				public boolean isCellEditable(int 		row,
											  int 		column)
				{
					return columnEditables[column];
				}
			});
		
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		
		for(int i = 0; i < table.getColumnCount(); ++i)
		{
			table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		table.setAutoCreateRowSorter(true);
		
		internalPanel.add(scrollPaneTabela);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_package	:
	 * @param	_row		:
	 */
	public void insertTableField(Package		_package,
								 int 			_row)
	{
		if(_row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(_package.getFlightTicketGoing().getSource(),
									_row, 
									0);
		
		table.getModel().setValueAt(_package.getFlightTicketGoing().getDest(),
									_row, 
									1);

		table.getModel().setValueAt(_package.getAccommodation().getAccommodationName(),
									_row, 
									2);

		table.getModel().setValueAt(_package.getFlightTicketGoing().getPrice(),
									_row, 
									3);

		table.getModel().setValueAt(_package.getFlightTicketReturn().getPrice(),
									_row, 
									4);

		table.getModel().setValueAt(_package.getAccommodation().getPrice(),
									_row, 
									5);
		
		table.getModel().setValueAt(_package.getTotalPrice(),
									_row, 
									6);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_list	:
	 */
	public void insertTableField(ArrayList<Package>	_list)
	{
		int row = 0;
		Package package;
		
		for (int i = 0; _list.size(); i++)
		{
			package = _list.get(i);

			insertTableField(package,
							 row++);
		}
	}
	
	/**
	 * @brief	This method will handle the initial configurations to the Search Button
	 */
	public void configButton()
	{
		buttonSearch = new JButton("Buscar Pacotes");
		
		// Settings for the Search Button
		buttonSearch.setBorder(new BevelBorder(BevelBorder.RAISED, 
											   null, 
											   null, 
											   null, 
											   null));
		
		buttonSearch.setBackground(new Color(238, 238, 238));
		buttonSearch.setBounds(10, 370,
							   350, 30);
		
		buttonSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(checkForEmptyFields())
				{
					JOptionPane.showMessageDialog(new JFrame(),
												  "Existem Campos n�o preenchidos!", 
												  "Erro",
												  JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					processSearchPackages();
				}
			}
		});
		
		internalPanel.add(buttonSearch);
	}
	
	/**
	 * @brief	This method will handle the initial setup to the Dates Labels and Calendars
	 */
	public void configDates()
	{
		JLabel labelDateOneWay				= new JLabel("Data de Ida: ");
		
		labelDateRoundTrip					= new JLabel("Data de Retorno: ");
		
		UtilDateModel  modelOneWay			= new UtilDateModel();
		UtilDateModel  modelRoundTrip		= new UtilDateModel();
		
		JDatePanelImpl datePanelOneWay		= new JDatePanelImpl(modelOneWay);
		JDatePanelImpl datePanelRoundTrip	= new JDatePanelImpl(modelRoundTrip);
		
		datePickerOneWayTrip 				= new JDatePickerImpl(datePanelOneWay);
		datePickerRoundTrip 				= new JDatePickerImpl(datePanelRoundTrip);
		
		// Label for One Way trip configurations
		labelDateOneWay.setPreferredSize(new Dimension(75, 15));
		labelDateOneWay.setBounds(10, 200,
						          120, 20);
		
		// Label for Round Trip configurations
		labelDateRoundTrip.setVisible(false);
		labelDateRoundTrip.setPreferredSize(new Dimension(80, 15));
		labelDateRoundTrip.setBounds(10, 250,
							         120, 20);
		
		// One Way Trip configurations
		datePickerOneWayTrip.setBounds(140, 197, 
									   200, 40);
		
		datePickerOneWayTrip.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		// Round Trip configurations
		datePickerRoundTrip.setVisible(false);
		datePickerRoundTrip.setBounds(140, 247, 
									  200, 40);
		
		datePickerRoundTrip.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!checkForPreviousDate(datePickerOneWayTrip, 
										 datePickerRoundTrip))
				{
					JOptionPane.showMessageDialog(new JFrame(),
												  "Data de Retorno n�o pode ser anterior � data de Ida!", 
												  "Erro",
												  JOptionPane.ERROR_MESSAGE);
					
					modelRoundTrip.setSelected(false);
				}
			}
		});
		
		internalPanel.add(labelDateOneWay);
		internalPanel.add(labelDateRoundTrip);
		internalPanel.add(datePickerOneWayTrip);
		internalPanel.add(datePickerRoundTrip);
	}
	
	/**
	 * @brief	Check whether or not the return Date is greater than the go Date
	 * 
	 * @param goDate		: JDatePicker for go Date
	 * @param returnDate	: JDatePicker for return Date
	 * 
	 * @return	True if return Date is lesser than Go Date
	 * 			False otherwise
	 */
	public boolean checkForPreviousDate(JDatePickerImpl	goDate,
										JDatePickerImpl	returnDate)
	{
		boolean returnValue = false;
		
		// Check Year
		if(returnDate.getModel().getYear() > goDate.getModel().getYear())
		{
			returnValue = true;
		}
		else
		{
			// Check Month
			if(returnDate.getModel().getMonth() > goDate.getModel().getMonth())
			{
				returnValue = true;
			}
			else
			{
				// Check Day
				if(returnDate.getModel().getDay() > goDate.getModel().getDay())
				{
					returnValue = true;
				}
			}
		}
		
		return returnValue;
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
		
		if(comboBoxStateSrc.getSelectedItem().equals("  ")		||
		   comboBoxCitySrc.getSelectedItem().equals("  ") 		||
		   comboBoxStateDest.getSelectedItem().equals("  ") 	||
		   comboBoxCityDest.getSelectedItem().equals("  ")		||
		   datePickerOneWayTrip.getModel().getValue() == null)
		{
			returnValue = true;
		}
		
		// If we are searching for a round trip, we should
		// look also to datePickerRoundTrip value
		if(radioButtonRoundWay.isSelected())
		{
			if(datePickerRoundTrip.getModel().getValue() == null)
			{
				returnValue = true;
			}
		}
		
		// Lastly, if the specify Hotel is checked,
		// we should check in the JTextField as well
		if(checkBoxSpecificHotel.isSelected())
		{
			if(textFieldHotel.getText().equals(""))
			{
				returnValue = true;
			}
		}
		
		return returnValue;
	}
	
	/**
	 * @brief	Initial settings for the Hotel name Search feature
	 */
	public void configHotelSearch()
	{
		labelHotel		= new JLabel("Nome do Hotel: ");
		
		textFieldHotel	= new JTextField();
		
		// Hotel Name label configurations
		labelHotel.setVisible(false);
		labelHotel.setPreferredSize(new Dimension(75, 15));
		labelHotel.setBounds(10, 310,
						     120, 20);
		
		// Hotel Name Text Field configurations
		textFieldHotel.setVisible(false);
		textFieldHotel.setPreferredSize(new Dimension(75, 15));
		textFieldHotel.setBounds(140, 310,
						         200, 20);
				
		internalPanel.add(labelHotel);
		internalPanel.add(textFieldHotel);
	}

	/**
	 * @brief
	 */
	public void cleanTable()
	{
		internalPanel.remove(scrollPaneTabela);
		
		configTable();
	}
	
	/**
	 * @brief
	 */
	public void processSearchButton() throws RemoteException
	{
		ArrayList<Package> list;
		FlightTicket flightTicketGoing;
		FlightTicket flightTicketReturn = null;
		Accommodation accommodation;

		Calendar calendar = Calendar.getInstance();
		
		int day 	= datePickerOneWayTrip.getModel().getDay();
	    int month 	= datePickerOneWayTrip.getModel().getMonth();
	    int year 	= datePickerOneWayTrip.getModel().getYear();
	    
	    calendar.set(year,
	    			 month,
	    			 day);
	 
		// First of all, we should clean the table
		cleanTable();

		// Fill the flight tickets and accommodation to be search
		flightTicketGoing.setSource(comboBoxCitySrc.getSelectedItem().toString());
		flightTicketGoing.setDest(comboBoxCityDest.getSelectedItem().toString());
		flightTicketGoing.setDate(new java.sql.Date(calendar.getTime().getTime());

		if(radioButtonRoundWay.isSelected())
		{
			day 	= datePickerRoundTrip.getModel().getDay();
		    month 	= datePickerRoundTrip.getModel().getMonth();
		    year 	= datePickerRoundTrip.getModel().getYear();
		    
		    calendar.set(year,
		    			 month,
		    			 day);

			flightTicketReturn.setDest(comboBoxCitySrc.getSelectedItem().toString());
			flightTicketReturn.setSource(comboBoxCityDest.getSelectedItem().toString());
			flightTicketReturn.setDate(new java.sql.Date(calendar.getTime().getTime());
		}

		accommodation.setCityName(comboBoxCityDest.getSelectedItem().toString());
		accommodation.setAccommodationName(textFieldHotel.getText().toString());
				
		list = serverReference.searchPackages(flightTicketGoing,
											  flightTicketReturn,
											  accommodation);
		
		if(0 == list.getAccommodationListSize())
		{
			JOptionPane.showMessageDialog(new JFrame(),
					  					  "Nenhum pacote encontrado!", 
					  					  "Aviso",
					  					  JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			insertTableField(list);
		}
	}
}
