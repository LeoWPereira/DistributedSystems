/**
 ******************************************************************************
 * @file    EventsPanel.java
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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import Classes.AccommodationInterest;
import Classes.FlightTicketInterest;
import Classes.PackageInterest;
import RMI.ServerServent;

/**
 * @brief	This Class will Handle every method from GUI "Events"
 */
public class EventsPanel extends JPanel
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
	 * @brief	Member containing a label to "Event type" info
	 */
	private static JLabel 		labelEventType;

	/**
	 * @brief	Member to store the group of Radio Buttons
	 */
	private static ButtonGroup 	group;
	
	/**
	 * @brief	Member containing the info about the "Flight-Ticket" Radio Button
	 */
	private static JRadioButton	radioButtonFlightTicket;
	
	/**
	 * @brief	Member containing the info about the "Accommodation" Radio Button
	 */
	private static JRadioButton	radioButtonAccommodation;

	/**
	 * @brief	Member containing the info about the "Package" Radio Button
	 */
	private static JRadioButton	radioButtonPackage;
	
	/**
	 * @brief	Member holding every info about the DB Consult STM
	 */
	private static Statement	dbStatement;

	/**
	 * @brief	Member containing a table where will be shown interests Info
	 */
	private JTable 			  table;
	
	/**
	 * @brief	Member containing info about the table scroll
	 */
	private JScrollPane 	  scrollPaneTabela;

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
	public EventsPanel(JPanel 			_panel,
					   Statement		_stm,
					   ServerServent 	_serverRMI) throws ParseException, SQLException
	{		
		internalPanel 	= _panel;
		
		dbStatement 	= _stm;

		serverRMI 		= _serverRMI;
		
		internalPanel.removeAll();
		
		configRadioButtons();
		
		internalPanel.updateUI();
	}
	
	/**
	 * @brief	Initial settings for the radio buttons and radio buttons group
	 */
	public void configRadioButtons()
	{
		labelEventType			= new JLabel("Escolha o tipo de registro de interesse:");
		
		// Event Type label configurations
		labelEventType.setPreferredSize(new Dimension(75, 15));
		labelEventType.setBounds(280, 5,
						    	 400, 20);

		group 					 = new ButtonGroup();
		
		radioButtonFlightTicket  = new JRadioButton("Passagens");
		radioButtonAccommodation = new JRadioButton("Hospedagens");
		radioButtonPackage		 = new JRadioButton("Pacotes");
		
		// Settings for the Flight Ticket Radio Button
		radioButtonFlightTicket.setSelected(false);
		radioButtonFlightTicket.setBounds(200, 20,
										  100, 40);
		
		radioButtonFlightTicket.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				showTicketsInterest();
			}
		});
		
		// Settings for the Accommodation Radio Button
		radioButtonAccommodation.setBounds(350, 20,
									  	   150, 40);
		
		radioButtonAccommodation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				showAccommodationInterest();
			}
		});

		// Settings for the Package Radio Button
		radioButtonPackage.setBounds(500, 20,
									 150, 40);
		
		radioButtonPackage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				showPackageInterest();
			}
		});
		
        group.add(radioButtonFlightTicket);
        group.add(radioButtonAccommodation);
        group.add(radioButtonPackage);

        internalPanel.add(labelEventType);        
        internalPanel.add(radioButtonFlightTicket);
        internalPanel.add(radioButtonAccommodation);
        internalPanel.add(radioButtonPackage);
	}

	/**
	 * @brief	The method will handle the initial settings for the table
	 */
	@SuppressWarnings("serial")
	public void configTicketInterestTable()
	{
		table 							= new JTable();
		scrollPaneTabela 				= new JScrollPane();
		
		DefaultTableCellRenderer dtcr	= new DefaultTableCellRenderer();
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Settings for the Scroll
		scrollPaneTabela.setBackground(new Color(222, 184, 135));
		scrollPaneTabela.setBorder(null);
		scrollPaneTabela.setViewportView(table);
		scrollPaneTabela.setBounds(100, 70,
								   600, 300);
		
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
			new String[] {"Cliente", "Tipo", "Origem", "Destino", "Quantidade", "Preco (Max)"})
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
		
		internalPanel.add(scrollPaneTabela);
	}

	/**
	 * @brief	The method will show the flight ticket interest list in the table
	 */
	public void showTicketsInterest()
	{    
		configTicketInterestTable();

		ArrayList<FlightTicketInterest> listTicketInterest = serverRMI.getTicketInterestList();
		
		int tableSize = listTicketInterest.size();
		
		if(0 == tableSize)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					  					  "Nenhum registro de interesse de passagem foi encontrado!", 
					  					  "Aviso",
					  					  JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			insertTableFieldTicket(listTicketInterest);
		}
	}

	/**
	 * @brief
	 * 
	 * @param	_ticketInterest	:
	 * @param	_row				:
	 */
	public void insertTableFieldTicket(FlightTicketInterest	_ticketInterest,
								 	   int 					_row)
	{
		if(_row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(_ticketInterest.getClientName(),
									_row, 
									0);

		if(_ticketInterest.isReturnTicket())
		{
			table.getModel().setValueAt("Ida/volta",
										_row, 
										1);
		}
		else
		{
			table.getModel().setValueAt("Ida",
										_row, 
										1);
		}

		table.getModel().setValueAt(_ticketInterest.getSource(),
									_row, 
									2);
		
		table.getModel().setValueAt(_ticketInterest.getDest(),
									_row, 
									3);

		table.getModel().setValueAt(_ticketInterest.getQuantity(),
									_row, 
									4);
		
		table.getModel().setValueAt(_ticketInterest.getMaxPrice(),
									_row, 
									5);
	}

	/**
	 * @brief
	 * 
	 * @param	_list	:
	 */
	public void insertTableFieldTicket(ArrayList<FlightTicketInterest>	_list)
	{
		int row = 0;
		FlightTicketInterest ticketInterest;

		for (int i = 0; i < _list.size(); i++) 
		{
            ticketInterest = _list.get(i);

            insertTableFieldTicket(ticketInterest,
							 	   row++);
        }
	}

	/**
	 * @brief	The method will handle the initial settings for the table of accommodation interests
	 */
	@SuppressWarnings("serial")
	public void configAccommodationInterestTable()
	{
		table 							= new JTable();
		scrollPaneTabela 				= new JScrollPane();
		
		DefaultTableCellRenderer dtcr	= new DefaultTableCellRenderer();
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Settings for the Scroll
		scrollPaneTabela.setBackground(new Color(222, 184, 135));
		scrollPaneTabela.setBorder(null);
		scrollPaneTabela.setViewportView(table);
		scrollPaneTabela.setBounds(100, 70,
								   600, 300);
		
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
			new String[] {"Cliente", "Cidade", "Nome Hospedagem", "Quantidade", "Nº Pessoas", "Preco (Max)"})
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
		
		internalPanel.add(scrollPaneTabela);
	}

	/**
	 * @brief	The method will show the accommodation interest list in the table
	 */
	public void showAccommodationInterest()
	{    
		configAccommodationInterestTable();

		ArrayList<AccommodationInterest> listAccommodationInterest = serverRMI.getAccommodationInterestList();
		
		int tableSize = listAccommodationInterest.size();
		
		if(0 == tableSize)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					  					  "Nenhum registro de interesse de hospedagens foi encontrado!", 
					  					  "Aviso",
					  					  JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			insertTableFieldAccommodation(listAccommodationInterest);
		}
	}

	/**
	 * @brief
	 * 
	 * @param	_accommodationInterest	: AccommodationInterest
	 * @param	_row					: int
	 */
	public void insertTableFieldAccommodation(AccommodationInterest	_accommodationInterest,
								 	   		  int 					_row)
	{
		if(_row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(_accommodationInterest.getClientName(),
									_row, 
									0);

		table.getModel().setValueAt(_accommodationInterest.getAccommodationCityName(),
									_row, 
									1);
		
		table.getModel().setValueAt(_accommodationInterest.getAccommodationName(),
									_row, 
									2);

		table.getModel().setValueAt(_accommodationInterest.getQuantity(),
									_row, 
									3);

		table.getModel().setValueAt(_accommodationInterest.getNumberOfGuests(),
									_row, 
									4);
		
		table.getModel().setValueAt(_accommodationInterest.getMaxPrice(),
									_row, 
									5);
	}

	/**
	 * @brief
	 * 
	 * @param	_list	:
	 */
	public void insertTableFieldAccommodation(ArrayList<AccommodationInterest>	_list)
	{
		int row = 0;
		AccommodationInterest accommodationInterest;

		for (int i = 0; i < _list.size(); i++) 
		{
            accommodationInterest = _list.get(i);

            insertTableFieldAccommodation(accommodationInterest,
							 	   		  row++);
        }
	}

	/**
	 * @brief	The method will handle the initial settings for the table of accommodation interests
	 */
	@SuppressWarnings("serial")
	public void configPackageInterestTable()
	{
		table 							= new JTable();
		scrollPaneTabela 				= new JScrollPane();
		
		DefaultTableCellRenderer dtcr	= new DefaultTableCellRenderer();
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Settings for the Scroll
		scrollPaneTabela.setBackground(new Color(222, 184, 135));
		scrollPaneTabela.setBorder(null);
		scrollPaneTabela.setViewportView(table);
		scrollPaneTabela.setBounds(100, 70,
								   600, 300);
		
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
			new String[] {"Cliente", "Tipo", "Origem", "Destino", "Quantidade", "No. Pessoas", "Preco (Max)"})
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
		
		internalPanel.add(scrollPaneTabela);
	}

	/**
	 * @brief	The method will show the package interest list in the table
	 */
	public void showPackageInterest()
	{    
		configPackageInterestTable();

		ArrayList<PackageInterest> listPackageInterest = serverRMI.getPackageInterestList();
		
		int tableSize = listPackageInterest.size();
		
		if(0 == tableSize)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					  					  "Nenhum registro de interesse de hospedagens foi encontrado!", 
					  					  "Aviso",
					  					  JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			insertTableFieldPackage(listPackageInterest);
		}
	}

	/**
	 * @brief
	 * 
	 * @param	_packageInterest	: PackageInterest
	 * @param	_row				: int
	 */
	public void insertTableFieldPackage(PackageInterest	_packageInterest,
								 	    int 			_row)
	{
		if(_row >= table.getRowCount())
		{
			((DefaultTableModel)table.getModel()).addRow(new Object[]{null, null});
		}
		
		table.getModel().setValueAt(_packageInterest.getClientName(),
									_row, 
									0);

		if(_packageInterest.isReturnTicket())
		{
			table.getModel().setValueAt("Ida/volta",
										_row, 
										1);
		}
		else
		{
			table.getModel().setValueAt("Ida",
										_row, 
										1);
		}

		table.getModel().setValueAt(_packageInterest.getSource(),
									_row, 
									2);
		
		table.getModel().setValueAt(_packageInterest.getDest(),
									_row, 
									3);

		table.getModel().setValueAt(_packageInterest.getQuantity(),
									_row, 
									4);

		table.getModel().setValueAt(_packageInterest.getNumberOfGuests(),
									_row, 
									5);
		
		table.getModel().setValueAt(_packageInterest.getMaxPrice(),
									_row, 
									6);
	}

	/**
	 * @brief
	 * 
	 * @param	_list	:
	 */
	public void insertTableFieldPackage(ArrayList<PackageInterest>	_list)
	{
		int row = 0;
		PackageInterest packageInterest;

		for (int i = 0; i < _list.size(); i++) 
		{
            packageInterest = _list.get(i);

            insertTableFieldPackage(packageInterest,
							 	   		  row++);
        }
	}
}
