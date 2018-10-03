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
import java.sql.SQLException;
import java.util.Calendar;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Classes.FlightTicket;
import Classes.FlightTicketManager;
import Database.Controller.CtrlPassages;
import Extra.CitiesBrazil;
import RMI.ServerServent;
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
	private static final long serialVersionUID = 1L;

	/**
	 * @brief	Member to store every GUI information on the current Panel
	 */
	private static JPanel 		internalPanel;
	
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
	 * @brief	Member containing a "Date Picker" for One Way trips
	 */
	private JDatePickerImpl   datePicker;

	/**
	 * @brief	Member containing the hotel name text field
	 */
	private JFormattedTextField	textFieldQuantity;

	/**
	 * @brief	Member containing the hotel name text field
	 */
	private JFormattedTextField	textFieldPrice;
	
	/**
	 * @brief
	 */
	private CtrlPassages ctrlPassages;
	
	/**
	 * @brief	Member holding every info about the DB Consult STM
	 */
	private static Statement	dbStatement;
	
	/**
	 * @brief
	 */
	private FlightTicketManager	ticketManager = new FlightTicketManager();

	/**
	 * @brief	The server RMI object to access the interests lists
	 */
	private ServerServent 		serverRMI;
	
	/**
	 * @brief	Default Constructor
	 * 
	 * @param	_panel	: Panel where the content will be stored
	 * @param	_stm	:	
	 */
	public PassagesPanel(JPanel 		_panel,
						 Statement		_stm,
						 ServerServent 	server) throws ParseException, SQLException
	{
		ctrlPassages	= new CtrlPassages();

		serverRMI 		= server;
		
		internalPanel 	= _panel;
		
		dbStatement 	= _stm;
		
		internalPanel.removeAll();
		
		configStateAndCities();
		
		configDates();
		
		configQuantity();
		
		configPrice();
		
		configButton();
		
		configTable();
		
		ticketManager = ctrlPassages.loadDBPassages(dbStatement);
		
		insertTableField(ticketManager);
		
		internalPanel.updateUI();
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
				                  140, 20);
		
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
				                   140, 20);
		
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
		
		scrollPaneTabela 				= new JScrollPane(table);
		
		DefaultTableCellRenderer dtcr	= new DefaultTableCellRenderer();
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Settings for the Table
		table.setShowGrid(false);
		table.setBorder(null);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(new Color(222, 184, 135));
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null}
					},
			new String[] {"Origem", "Destino", "Data", "Quantidade", "Preço (R$)"})
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
		
		table.getColumnModel().getColumn(0).setMinWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		
		for(int i = 0; i < table.getColumnCount(); ++i)
		{
			table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		table.setAutoCreateRowSorter(true);
		
		// Settings for the Scroll
		scrollPaneTabela.setBorder(null);
		scrollPaneTabela.setViewportView(table);
		scrollPaneTabela.setBounds(330, 80,
								   440, 321);
		
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
		
		table.getModel().setValueAt(_passage.getDate(),
									_row, 
									2);
		
		table.getModel().setValueAt(_passage.getQuantity(),
									_row, 
									3);
		
		table.getModel().setValueAt(_passage.getPrice(),
									_row, 
									4);
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
	 * @brief	This method will handle the initial configurations to the Search Button
	 */
	public void configButton()
	{
		buttonSearch = new JButton("Registrar Trecho");
		
		// Settings for the Search Button
		buttonSearch.setBorder(new BevelBorder(BevelBorder.RAISED, 
											   null, 
											   null, 
											   null, 
											   null));
		
		buttonSearch.setBackground(new Color(238, 238, 238));
		buttonSearch.setBounds(10, 370,
							   310, 30);
		
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
					processRegistry();
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
		JLabel labelDate			= new JLabel("Data: ");
		
		UtilDateModel  model		= new UtilDateModel();
		
		JDatePanelImpl datePanel	= new JDatePanelImpl(model);
		
		datePicker	 				= new JDatePickerImpl(datePanel);
		
		// Label for One Way trip configurations
		labelDate.setPreferredSize(new Dimension(75, 15));
		labelDate.setBounds(10, 200,
						    120, 20);
		
		// One Way Trip configurations
		datePicker.setBounds(80, 197, 
							 220, 40);
		
		datePicker.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		internalPanel.add(labelDate);
		internalPanel.add(datePicker);
	}
	
	/**
	 * @brief
	 */
	public void configQuantity() throws ParseException
	{
		JLabel label		= new JLabel("Quantidade: ");
		
		MaskFormatter mask	= new MaskFormatter("###");
		mask.setValidCharacters("0123456789");
		
		textFieldQuantity	= new JFormattedTextField(mask);
	    
		label.setPreferredSize(new Dimension(75, 25));
		label.setBounds(10, 260,
						120, 20);
		
		textFieldQuantity.setPreferredSize(new Dimension(75, 25));
		textFieldQuantity.setBounds(80, 260,
						            80, 20);
	    
		internalPanel.add(label);
		internalPanel.add(textFieldQuantity);
	}
	
	/**
	 * @brief
	 */
	public void configPrice() throws ParseException
	{
		JLabel label		= new JLabel("Preço (R$): ");
		
		MaskFormatter mask	= new MaskFormatter("R$ ###.##");
		mask.setValidCharacters("0123456789");
		
		textFieldPrice		= new JFormattedTextField(mask);
	    
		label.setPreferredSize(new Dimension(75, 25));
		label.setBounds(10, 300,
						120, 20);
		
	    textFieldPrice.setPreferredSize(new Dimension(75, 25));
	    textFieldPrice.setBounds(80, 300,
						         80, 20);
		
	    internalPanel.add(label);
	    internalPanel.add(textFieldPrice);
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
		   datePicker.getModel().getValue() == null				||
		   textFieldQuantity.getText().equals("")				||
		   textFieldPrice.getText().equals("R$    .  "))
		{
			returnValue = true;
		}
		
		return returnValue;
	}
	
	/**
	 * @brief
	 */
	public void processRegistry()
	{
		try
		{
			int day 	= datePicker.getModel().getDay();
		    int month 	= datePicker.getModel().getMonth();
		    int year 	= datePicker.getModel().getYear();
		    
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(year, month, day);
			
		    // Insert Entry on the database
			ctrlPassages.insertEntry(dbStatement,
									 comboBoxCitySrc.getSelectedItem().toString(), 
							 		 comboBoxCityDest.getSelectedItem().toString(),
							 		 new java.sql.Date(calendar.getTime().getTime()),
							 		 Integer.valueOf(textFieldQuantity.getText().toString()),
							 		 Float.valueOf(textFieldPrice.getText().substring(3, 
							 				 								   		  9)));
			
			FlightTicket entry = new FlightTicket(comboBoxCitySrc.getSelectedItem().toString(), 
												  comboBoxCityDest.getSelectedItem().toString(), 
												  new java.sql.Date(calendar.getTime().getTime()), 
												  Integer.valueOf(textFieldQuantity.getText().toString()), 
												  Float.valueOf(textFieldPrice.getText().substring(3, 
			   		  										   									   9)));
			
			// We will also insert the same entry in our list
			ticketManager.insertFlightTicket(entry);
			
			// Finally, we update our table
			insertTableField(entry,
							 ticketManager.getFlightTicketListSize() - 1);
			
			JOptionPane.showMessageDialog(new JFrame(),
										  "Passagem inserida com sucesso!", 
										  "Sucesso",
										  JOptionPane.INFORMATION_MESSAGE);

			try 
			{
				serverRMI.notifyTicketsInterests(entry);
				serverRMI.notifyPackageInterests(entry);
			} 
			catch (RemoteException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		}
		catch (MySQLIntegrityConstraintViolationException e)
		{
			JOptionPane.showMessageDialog(new JFrame(),
										  "Passagem já existe no Banco de Dados!", 
										  "Erro",
										  JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
