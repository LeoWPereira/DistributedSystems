/**
 ******************************************************************************
 * @file    PassagesPanel.java
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
import java.text.ParseException;
import java.util.Calendar;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Classes.FlightTicket;
import Classes.FlightTicketInterest;
import Classes.FlightTicketManager;
import Extra.CitiesBrazil;
import RMI.ClientServent;
import RMI.ServerInterface;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * @brief	This Class will Handle every method from GUI "Passages"
 */
public class PassagesPanel extends JPanel
{
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long 	serialVersionUID = 1311564096576338404L;

	/**
	 * @brief	Member to store every GUI information on the current Panel
	 */
	private static JPanel 		internalPanel;
	
	/**
	 * @brief
	 */
	private static ServerInterface serverReference;
	
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
	 * @brief
	 */
	private static ClientServent clientRMI;
	
	/**
	 * @brief	Default Constructor
	 * 
	 * @param	panel	: Panel where the content will be stored
	 * @param	server	:
	 */
	public PassagesPanel(JPanel 			panel,
						 ServerInterface	server,
						 ClientServent 		client)
	{
		internalPanel 	= panel;
		
		serverReference	= server;

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
		comboBoxCityDest.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxStateDest.getItemAt(comboBoxStateDest.getSelectedIndex()))));
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
			new String[] {"Origem", "Destino", "Preço (R$)"})
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
		
		table.getColumnModel().getColumn(0).setMinWidth(107);
		table.getColumnModel().getColumn(1).setPreferredWidth(108);
		table.getColumnModel().getColumn(2).setPreferredWidth(35);
		
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
	 * @brief	The method will handle the insertion of data into the table
	 * 
	 * @param 	row		: Desired Row to add the data
	 * @param 	column	: Desired Column to add the data
	 * @param 	value	: Desired String value
	 */
	public void insertTableField(int 	row,
								 int 	column,
								 String	value)
	{
		if(row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(value, 
									row, 
									column);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_passage	:
	 * @param	_row		:
	 */
	public void insertTableField(FlightTicket	_passage,
								 int 			_row)
	{
		if(_row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(_passage.getSource(),
									_row, 
									0);
		
		table.getModel().setValueAt(_passage.getDest(),
									_row, 
									1);
		
		table.getModel().setValueAt(_passage.getPrice(),
									_row, 
									2);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_list	:
	 */
	public void insertTableField(FlightTicketManager	_list)
	{
		int row = 0;
		
		for (FlightTicket ticket : _list.getFlightTicketList())
		{
			insertTableField(ticket,
							 row++);
		}
	}
	
	/**
	 * @brief
	 * 
	 * @param row		:
	 * @param column	:
	 * 
	 * @return
	 */
	public String getTableField(int 	row,
			 					int 	column)
	{
		return (String)table.getModel().getValueAt(row, 
						    			   		   column);
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
	 * @brief	This method will handle the initial configurations to the Search Button
	 */
	public void configButton()
	{
		buttonSearch 	= new JButton("Buscar Passagens");
		buttonInterest 	= new JButton("Registrar interesse");
		
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
					try 
					{
						processInterestButton(arg0);
					}
					catch(ParseException e) 
					{
						e.printStackTrace();
					}
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
		
		// Lastly, if we are searching for a round trip, we should
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
	public void processSearchButton() throws RemoteException
	{
		Calendar calendar = Calendar.getInstance();
		
		// First of all, we should clean the table
		cleanTable();
		
		int day 	= datePickerOneWayTrip.getModel().getDay();
	    int month 	= datePickerOneWayTrip.getModel().getMonth();
	    int year 	= datePickerOneWayTrip.getModel().getYear();
	    
	    calendar.set(year,
	    			 month,
	    			 day);
	    
		FlightTicketManager list = serverReference.searchPassages(comboBoxCitySrc.getSelectedItem().toString(),
				   					   							  comboBoxCityDest.getSelectedItem().toString(),
				   					   							  new java.sql.Date(calendar.getTime().getTime()));
		
		int tableSize = list.getFlightTicketListSize();
		
		if(0 == tableSize)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					  					  "Nenhuma passagem de ida foi encontrada!", 
					  					  "Aviso",
					  					  JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			insertTableField(list);
		}
		
		if(radioButtonRoundWay.isSelected())
		{
			day 	= datePickerRoundTrip.getModel().getDay();
		    month 	= datePickerRoundTrip.getModel().getMonth();
		    year 	= datePickerRoundTrip.getModel().getYear();
		    
		    calendar.set(year,
		    			 month,
		    			 day);
		    
		    list = serverReference.searchPassages(comboBoxCityDest.getSelectedItem().toString(),
   					   					 		  comboBoxCitySrc.getSelectedItem().toString(),
   					   					   		  new java.sql.Date(calendar.getTime().getTime()));
		    
		    if(0 == list.getFlightTicketListSize())
			{
				JOptionPane.showMessageDialog(new JFrame(),
						  					  "Nenhuma passagem de volta foi encontrada!", 
						  					  "Aviso",
						  					  JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				for(FlightTicket ticket : list.getFlightTicketList())
			    {
			    	insertTableField(ticket,
			    					 tableSize++);
				}
			}
		}
	}
	
	/**
	 * @brief
	 */
	public void processTableSelection()
	{
		try 
    	{
			Calendar calendar = Calendar.getInstance();
			
    		if(table.getValueAt(table.getSelectedRow(), 0).toString().equals(comboBoxCitySrc.getSelectedItem().toString())	&&
    		   table.getValueAt(table.getSelectedRow(), 1).toString().equals(comboBoxCityDest.getSelectedItem().toString()))
    		{
    			// We selected row with a passage from source to dest
    			
    			int day 	= datePickerOneWayTrip.getModel().getDay();
    		    int month 	= datePickerOneWayTrip.getModel().getMonth();
    		    int year 	= datePickerOneWayTrip.getModel().getYear();
    		    
    		    calendar.set(year,
    		    			 month,
    		    			 day);
    		    
    			PassagesDetailsPanel detailedPanel = new PassagesDetailsPanel(serverReference,
    																		  comboBoxStateSrc.getSelectedItem().toString(),
    																		  table.getValueAt(table.getSelectedRow(), 0).toString(),
    																		  comboBoxStateDest.getSelectedItem().toString(),
						  													  table.getValueAt(table.getSelectedRow(), 1).toString(),
						  													  calendar.getTime(),
						  													  Float.valueOf(table.getValueAt(table.getSelectedRow(), 2).toString()));

    			detailedPanel.setVisible(true);
    		}
    		else if(table.getValueAt(table.getSelectedRow(), 0).toString().equals(comboBoxCityDest.getSelectedItem().toString())	&&
    	    		table.getValueAt(table.getSelectedRow(), 1).toString().equals(comboBoxCitySrc.getSelectedItem().toString()))
    		{
    			// Selected Row is a passage from dest to source
    			
    			int day 	= datePickerRoundTrip.getModel().getDay();
    		    int month 	= datePickerRoundTrip.getModel().getMonth();
    		    int year 	= datePickerRoundTrip.getModel().getYear();
    		    
    		    calendar.set(year,
    		    			 month,
    		    			 day);
    		    
    			PassagesDetailsPanel detailedPanel = new PassagesDetailsPanel(serverReference,
    																		  comboBoxStateDest.getSelectedItem().toString(),
    																		  table.getValueAt(table.getSelectedRow(), 0).toString(),
    																		  comboBoxStateSrc.getSelectedItem().toString(),
						  													  table.getValueAt(table.getSelectedRow(), 1).toString(),
						  													  new java.sql.Date(calendar.getTime().getTime()),
						  													  Float.valueOf(table.getValueAt(table.getSelectedRow(), 2).toString()));

    			detailedPanel.setVisible(true);
    		}
    	}
    	catch (NumberFormatException e) 
    	{
			e.printStackTrace();
		} 
    	catch (ParseException e) 
    	{
			e.printStackTrace();
		}
	}

	/**
	 * @brief
	 */
	private void processInterestButton(java.awt.event.ActionEvent evt) throws ParseException
	{
		MaskFormatter maskPrice	= new MaskFormatter("R$ ###.##");
		maskPrice.setValidCharacters("0123456789");
		
		MaskFormatter maskQuantity	= new MaskFormatter("###");
		maskQuantity.setValidCharacters("0123456789");
		
		JFormattedTextField maxPrice = new JFormattedTextField(maskPrice);
		JFormattedTextField quantity = new JFormattedTextField(maskQuantity);
		
        Object[] message = {"Preço Máximo:", maxPrice, "Quantidade:", quantity};

        int response = JOptionPane.showConfirmDialog(null, message, "Registro de interesse", JOptionPane.OK_CANCEL_OPTION);
        
        if (response == JOptionPane.OK_OPTION) 
        {
        	FlightTicket flightTicketTo = null;
        	FlightTicket flightTicketFrom = null;

            float maxPriceFloat = Float.valueOf(maxPrice.getText().substring(3, 
			   		  														 9));
            Calendar calendar = Calendar.getInstance();
		
			int day 	= datePickerOneWayTrip.getModel().getDay();
		    int month 	= datePickerOneWayTrip.getModel().getMonth();
		    int year 	= datePickerOneWayTrip.getModel().getYear();
		    
		    calendar.set(year,
		    			 month,
		    			 day);
		    
			Date goingDate	= new java.sql.Date(calendar.getTime().getTime());

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
			    
			    Date returnDate	= new java.sql.Date(calendar.getTime().getTime());

			    flightTicketFrom = new FlightTicket(comboBoxCityDest.getSelectedItem().toString(),
			    								  	comboBoxCitySrc.getSelectedItem().toString(),
   					   					 	  	  	returnDate,
   					   					 	  	  	0,
   					   					 	  	  	0);
			}

			try 
            {
                serverReference.registerPassageInterest(flightTicketTo, 
                										flightTicketFrom,
                										Integer.valueOf(quantity.getText()),
                										maxPriceFloat,
                										clientRMI,
                										clientRMI.getClientName());

                boolean isReturnTicket = true;

                if(flightTicketFrom != null)
                {
                	isReturnTicket = true;
                }

                FlightTicketInterest ticketInterest = new FlightTicketInterest(flightTicketTo,
        															   flightTicketFrom, 
        															   isReturnTicket,
        															   Integer.valueOf(quantity.getText()), 
        															   maxPriceFloat,
        															   clientRMI,
        															   clientRMI.getClientName());

       			clientRMI.addFlightTicketInterest(ticketInterest);
            } 
            catch (RemoteException e) 
			{
				e.printStackTrace();
            }
        }
    }
}
