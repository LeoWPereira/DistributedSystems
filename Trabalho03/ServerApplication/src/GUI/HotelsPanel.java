/**
 ******************************************************************************
 * @file    HotelsPanel.java
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Database.Controller.CtrlHotel;
import Extra.CitiesBrazil;
import RMI.ServerServent;

/**
 * @brief	This Class will Handle every method from GUI "Hotels"
 */
public class HotelsPanel extends JPanel
{
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 7468938695192643425L;

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
	private JComboBox<String> comboBoxState;
	
	/**
	 * @brief	Member containing a Combo Box with the Source City
	 */
	private JComboBox<String> comboBoxCity;
	
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
	 * @brief	Member containing a label to "Town" info
	 */
	private JLabel 			  labelTown;
	
	/**
	 * @brief	Member containing a label to "Hotel" info
	 */
	private JLabel 			  labelHotel;
	
	/**
	 * @brief	Member containing the hotel name text field
	 */
	private JTextField 		  textFieldHotel;
	
	/**
	 * @brief	Member containing the quantity of available rooms
	 */
	private JFormattedTextField	textFieldQuantity;

	/**
	 * @brief	Member containing the max number of quests per room
	 */
	private JFormattedTextField	textFieldMaxGuestsRoom;
	
	/**
	 * @brief	Member containing the room price
	 */
	private JFormattedTextField	textFieldPrice;
	
	/**
	 * @brief
	 */
	private CtrlHotel ctrlHotel;
	
	/**
	 * @brief	Member holding every info about the DB Consult STM
	 */
	private static Statement	dbStatement;
	
	/**
	 * @brief
	 */
	private AccommodationManager	hotelManager = new AccommodationManager();

	/**
	 * @brief	The server RMI object to access the interests lists
	 */
	private ServerServent 		serverRMI;
	
	/**
	 * @brief	Default Constructor
	 * 
	 * This constructor will first remove everything from the JPanel
	 * 
	 * @param	_panel	:	JPanel containing this panel future info
	 * @param	_stm	:	
	 */
	public HotelsPanel(JPanel 			_panel,
					   Statement		_stm,
					   ServerServent 	_server) throws ParseException, SQLException
	{
		ctrlHotel		= new CtrlHotel();
		
		internalPanel 	= _panel;
		
		dbStatement 	= _stm;

		serverRMI 		= _server;
		
		internalPanel.removeAll();
		
		configStateAndCities();
		
		configHotelSearch();
		
		configQuantity();
		
		configMaxGuestsByRoom();
		
		configPrice();
		
		configButton();
		
		configTable();
		
		hotelManager = ctrlHotel.loadDBHotels(dbStatement);
		
		insertTableField(hotelManager);
		
		internalPanel.updateUI();
	}
	
	/**
	 * @brief	Initial settings for the several ComboBox of the Panel
	 */
	public void configStateAndCities()
	{
		brazil 				= new CitiesBrazil();
		
		comboBoxState 		= new JComboBox<String>();
		comboBoxCity		= new JComboBox<String>();
		
		labelTown			= new JLabel("Município: ");
		
		// Town label configurations
		labelTown.setPreferredSize(new Dimension(75, 15));
		labelTown.setBounds(10, 80,
						    85, 20);
		
		// Settings for the State Source comboBox
		comboBoxState.setModel(new DefaultComboBoxModel<String>(brazil.getStates()));
		comboBoxState.setBounds(80, 80, 
								60, 20);
		
		comboBoxState.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				changeCitiesComboBox(comboBoxCity,
									 comboBoxState.getItemAt(comboBoxState.getSelectedIndex()));
			}
		});
		
		// Settings for the City Source comboBox
		comboBoxCity.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxState.getItemAt(comboBoxState.getSelectedIndex()))));
		comboBoxCity.setBounds(160, 80, 
				               140, 20);
				
		internalPanel.add(labelTown);
		internalPanel.add(comboBoxState);
		internalPanel.add(comboBoxCity);
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
	 * @brief	Initial settings for the Hotel name Search feature
	 */
	public void configHotelSearch()
	{
		labelHotel		= new JLabel("Nome do Hotel: ");
		
		textFieldHotel	= new JTextField();
		
		// Hotel Name label configurations
		labelHotel.setPreferredSize(new Dimension(75, 15));
		labelHotel.setBounds(10, 130,
						     120, 20);
		
		// Hotel Name Text Field configurations
		textFieldHotel.setPreferredSize(new Dimension(75, 15));
		textFieldHotel.setBounds(120, 130,
						         180, 20);
				
		internalPanel.add(labelHotel);
		internalPanel.add(textFieldHotel);
	}
	
	/**
	 * @brief
	 */
	public void configQuantity() throws ParseException
	{
		JLabel label		= new JLabel("Quantidade de Quartos: ");
		
		MaskFormatter mask	= new MaskFormatter("###");
		mask.setValidCharacters("0123456789");
		
		textFieldQuantity	= new JFormattedTextField(mask);
	    
		label.setPreferredSize(new Dimension(160, 25));
		label.setBounds(10, 175,
						160, 20);
		
		textFieldQuantity.setPreferredSize(new Dimension(75, 25));
		textFieldQuantity.setBounds(160, 175,
						            80, 20);
	    
		internalPanel.add(label);
		internalPanel.add(textFieldQuantity);
	}
	
	/**
	 * @brief
	 */
	public void configMaxGuestsByRoom() throws ParseException
	{
		JLabel label		= new JLabel("Max. Pessoas / Quarto: ");
		
		MaskFormatter mask	= new MaskFormatter("#");
		mask.setValidCharacters("0123456789");
		
		textFieldMaxGuestsRoom	= new JFormattedTextField(mask);
	    
		label.setPreferredSize(new Dimension(160, 25));
		label.setBounds(10, 215,
						160, 20);
		
		textFieldMaxGuestsRoom.setPreferredSize(new Dimension(75, 25));
		textFieldMaxGuestsRoom.setBounds(160, 215,
						                 80, 20);
	    
		internalPanel.add(label);
		internalPanel.add(textFieldMaxGuestsRoom);
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
		label.setBounds(10, 255,
						160, 20);
		
	    textFieldPrice.setPreferredSize(new Dimension(75, 25));
	    textFieldPrice.setBounds(160, 255,
						         80, 20);
		
	    internalPanel.add(label);
	    internalPanel.add(textFieldPrice);
	}
	
	/**
	 * @brief	This method will handle the initial configurations to the Search Button
	 */
	public void configButton()
	{
		buttonSearch = new JButton("Registrar Hospedagem");
		
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
	 * @brief	Check whether or not there is an (or more) empty field(s) to be filled
	 * 
	 * @return	True if there is at least one empty field
	 * 			False otherwise
	 */
	public boolean checkForEmptyFields()
	{
		boolean returnValue = false;
		
		if(comboBoxState.getSelectedItem().equals("  ")	||
		   comboBoxCity.getSelectedItem().equals("  ")	||
		   textFieldHotel.getText().equals("")			||
		   textFieldQuantity.getText().equals("")		||
		   textFieldMaxGuestsRoom.getText().equals("")	||
		   textFieldPrice.getText().equals("R$    .  "))
		{
			returnValue = true;
		}
		
		return returnValue;
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
			new String[] {"Cidade", "Hotel", "Quartos", "Pessoas / Quarto", "Preço (R$)"})
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
		
		table.getColumnModel().getColumn(0).setMinWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
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
	public void insertTableField(Accommodation	_hotel,
								 int 			_row)
	{
		if(_row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(_hotel.getCityName(),
									_row, 
									0);
		
		table.getModel().setValueAt(_hotel.getAccommodationName(),
									_row, 
									1);
		
		table.getModel().setValueAt(_hotel.getQuantity(),
									_row, 
									2);
		
		table.getModel().setValueAt(_hotel.getMaxGuestsPerRoom(),
									_row, 
									3);
		
		table.getModel().setValueAt(_hotel.getPrice(),
									_row, 
									4);
	}
	
	/**
	 * @brief
	 * 
	 * @param	_list	:
	 */
	public void insertTableField(AccommodationManager	_list)
	{
		int row = 0;
		
		for (Accommodation value : _list.getAccommodationList())
		{
			insertTableField(value,
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
	public void processRegistry()
	{
		try
		{
			// Insert Entry on the database
			ctrlHotel.insertEntry(dbStatement,
								  comboBoxCity.getSelectedItem().toString(), 
								  textFieldHotel.getText().toString(), 
								  Integer.valueOf(textFieldQuantity.getText().toString()), 
								  Integer.valueOf(textFieldMaxGuestsRoom.getText().toString()),
								  Float.valueOf(textFieldPrice.getText().substring(3, 
		   									   									   9)));
			
			// We will also insert the same entry in our list
			Accommodation entry = new Accommodation(comboBoxCity.getSelectedItem().toString(), 
													textFieldHotel.getText().toString(), 
													Integer.valueOf(textFieldQuantity.getText().toString()), 
													Integer.valueOf(textFieldMaxGuestsRoom.getText().toString()), 
													Float.valueOf(textFieldPrice.getText().substring(3, 
							   									   									 9)));
			
			// Finally, we update our table
			hotelManager.insertAccommodation(entry);
			
			// Finally, we update our table
			insertTableField(entry,
							 hotelManager.getAccommodationListSize() - 1);
			
			JOptionPane.showMessageDialog(new JFrame(),
										  "Hospedagem inserida com sucesso!", 
										  "Sucesso",
										  JOptionPane.INFORMATION_MESSAGE);

			serverRMI.notifyAccommodationInterests(entry);
			serverRMI.notifyPackageInterests(entry);
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
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
