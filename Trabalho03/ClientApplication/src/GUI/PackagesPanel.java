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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Extra.CitiesBrazil;
import RMI.ClientServent;
import RMI.ServerInterface;
import Classes.Accommodation;
import Classes.FlightTicket;
import Classes.Packages;
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
	 * @brief
	 */
	private static ServerInterface serverReference;
	
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
	 * @brief	Member containing the register interest button
	 */
	private JButton 		  buttonInterest;
	
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
	 * @brief	Member containing the client servent object
	 */
	private ClientServent 	  clientRMI;
	
	/**
	 * @brief	Default Constructor
	 * 
	 * @param	JPanel			panel
	 * @param 	ClientServent 	client
	 * @param 	ServerInterface server
	 */
	public PackagesPanel(JPanel 			panel,
			 		     ServerInterface	server,
			 		     ClientServent 		client)
	{
		internalPanel = panel;

		serverReference = server;

		clientRMI = client;
		
		internalPanel.removeAll();
		
		configRadioButtons();
		
		configStateAndCities();
		
		configDates();
		
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
			new String[] {"Origem", "Destino", "Hotel", "Preço Ida", "Preço Volta", "Preço Hospedagem", "Preço Total"})
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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{
			boolean alreadyClicked = false;
			
		    public void valueChanged(ListSelectionEvent event) 
		    {
		    	if(!alreadyClicked)
		    	{
			        if(table.getSelectedRow() > -1)
			        {
			        	processTableSelection();
			        }
			        
			        alreadyClicked = true;
		    	}
		    	else
		    	{
		    		alreadyClicked = false;
		    	}
		    }
		});
		
		internalPanel.add(scrollPaneTabela);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_package	:
	 * @param	_row		:
	 */
	public void insertTableField(Packages		_package,
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

		if(_package.getFlightTicketReturn() != null)
		{
			table.getModel().setValueAt(_package.getFlightTicketReturn().getPrice(),
										_row, 
										4);
		}

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
	public void insertTableField(ArrayList<Packages>	_list)
	{
		int row = 0;
		Packages pack;
		
		for (int i = 0; i < _list.size(); i++)
		{
			pack = _list.get(i);

			insertTableField(pack,
							 row++);
		}
	}
	
	/**
	 * @brief	This method will handle the initial configurations to the Search Button
	 */
	public void configButton()
	{
		buttonSearch = new JButton("Buscar Pacotes");
		buttonInterest = new JButton("Registrar Interesse");
		
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
												  "Existem Campos não preenchidos!", 
												  "Erro",
												  JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						processSearchButton();
					} 
					catch (RemoteException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		// Settings for the Register Interest Button
		buttonInterest.setBorder(new BevelBorder(BevelBorder.RAISED, 
											   null, 
											   null, 
											   null, 
											   null));
		
		buttonInterest.setBackground(new Color(238, 238, 238));
		buttonInterest.setBounds(10, 330,
							   350, 30);

		buttonInterest.addActionListener(new ActionListener()
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
					processInterestButton(arg0);
				}
			}
		});
		
		internalPanel.add(buttonSearch);
		internalPanel.add(buttonInterest);
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
												  "Data de Retorno não pode ser anterior à data de Ida!", 
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
		
		return returnValue;
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
	public void processTableSelection()
	{
		Calendar calendarGoing = Calendar.getInstance();
		Calendar calendarReturn = Calendar.getInstance();

		// We selected row with a passage from source to dest	
		int day 	= datePickerOneWayTrip.getModel().getDay();
	    int month 	= datePickerOneWayTrip.getModel().getMonth();
	    int year 	= datePickerOneWayTrip.getModel().getYear();
	    
	    calendarGoing.set(year,
	    			 month,
	    			 day);

	    // We selected row with a passage from dest to source	
		day 	= datePickerRoundTrip.getModel().getDay();
	    month 	= datePickerRoundTrip.getModel().getMonth();
	    year 	= datePickerRoundTrip.getModel().getYear();
	    
	    calendarReturn.set(year,
	    			 month,
	    			 day);

	    float returnPrice = 0;
	    
	    if(radioButtonRoundWay.isSelected())
	    {
	    	returnPrice = Float.valueOf(table.getValueAt(table.getSelectedRow(), 4).toString());
	    }
    		    
		try 
    	{
			PackageDetailsPanel detailedPanel = new PackageDetailsPanel(serverReference,
																		comboBoxStateSrc.getSelectedItem().toString(),
																		table.getValueAt(table.getSelectedRow(), 0).toString(),
																		comboBoxStateDest.getSelectedItem().toString(),
																		table.getValueAt(table.getSelectedRow(), 1).toString(),
																		calendarGoing.getTime(),
																		calendarReturn.getTime(),
																		table.getValueAt(table.getSelectedRow(), 2).toString(),
																	  	Float.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString()),
																	  	returnPrice,
																	  	Float.valueOf(table.getValueAt(table.getSelectedRow(), 5).toString()));
			detailedPanel.setVisible(true);
    	}
		catch (java.text.ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief
	 */
	public void processSearchButton() throws RemoteException
	{
		ArrayList<Packages> list;
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
		flightTicketGoing = new FlightTicket(comboBoxCitySrc.getSelectedItem().toString(),
											 comboBoxCityDest.getSelectedItem().toString(),
											 calendar.getTime(),
											 0,
											 0);

		if(radioButtonRoundWay.isSelected())
		{
			day 	= datePickerRoundTrip.getModel().getDay();
		    month 	= datePickerRoundTrip.getModel().getMonth();
		    year 	= datePickerRoundTrip.getModel().getYear();
		    
		    calendar.set(year,
		    			 month,
		    			 day);

			flightTicketReturn = new FlightTicket(comboBoxCityDest.getSelectedItem().toString(),
												 comboBoxCitySrc.getSelectedItem().toString(),
												 calendar.getTime(),
												 0,
												 0);
		}

		accommodation = new Accommodation(comboBoxCityDest.getSelectedItem().toString(),
										  "",
										  0,
										  0,
										  0);
				
		list = serverReference.searchPackages(flightTicketGoing,
											  flightTicketReturn,
											  accommodation);
		
		if(0 == list.size())
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

	/**
	 * @brief
	 */
	private void processInterestButton(java.awt.event.ActionEvent evt) 
	{
        JTextField maxPrice = new JTextField();
        JTextField quantity = new JTextField();
        JTextField guests = new JTextField();
        Object[] message = {"Preco total maximo:", maxPrice, "Quantidade:", quantity, "No. de pessoas:", guests};

        int response = JOptionPane.showConfirmDialog(null, message, "Registro de interesse", JOptionPane.OK_CANCEL_OPTION);
        
        if (response == JOptionPane.OK_OPTION) 
        {
        	FlightTicket flightTicketTo = null;
        	FlightTicket flightTicketFrom = null;
        	Accommodation accommodation = null;

            float maxPriceFloat = Float.valueOf(maxPrice.getText());
            Calendar calendar = Calendar.getInstance();
		
			int day 	= datePickerOneWayTrip.getModel().getDay();
		    int month 	= datePickerOneWayTrip.getModel().getMonth();
		    int year 	= datePickerOneWayTrip.getModel().getYear();
		    
		    calendar.set(year,
		    			 month,
		    			 day);
		    
			Date goingDate	= calendar.getTime();

			flightTicketTo = new FlightTicket(comboBoxCitySrc.getSelectedItem().toString(),
											  comboBoxCityDest.getSelectedItem().toString(),
   					   					 	  goingDate,
   					   					 	  0,
   					   					 	  0);

			if(radioButtonRoundWay.isSelected())
			{
				day 	= datePickerRoundTrip.getModel().getDay();
			    month 	= datePickerRoundTrip.getModel().getMonth();
			    year 	= datePickerRoundTrip.getModel().getYear();
			    
			    calendar.set(year,
			    			 month,
			    			 day);
			    
			    Date returnDate	= calendar.getTime();

			    flightTicketFrom = new FlightTicket(comboBoxCityDest.getSelectedItem().toString(),
			    								  	comboBoxCitySrc.getSelectedItem().toString(),
   					   					 	  	  	returnDate,
   					   					 	  	  	0,
   					   					 	  	  	0);
			}

			accommodation = new Accommodation(comboBoxCityDest.getSelectedItem().toString(),
											  "",
											  0,
											  0,
											  0);

			try 
            {
                serverReference.registerPackageInterest(flightTicketTo, 
                										flightTicketFrom,
                										accommodation,
                										Integer.valueOf(quantity.getText()),
                										maxPriceFloat,
                										Integer.valueOf(guests.getText()),
                										clientRMI,
                										clientRMI.getClientName());
            } 
            catch (RemoteException e) 
			{
				e.printStackTrace();
            }
        }
    }
}
