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
	 * @brief	Default Constructor
	 * 
	 * This constructor will first remove everything from the JPanel
	 * 
	 * @param	_panel	:	JPanel containing this panel future info
	 * @param	_stm	:	
	 */
	public EventsPanel(JPanel 		_panel,
					   Statement	_stm) throws ParseException, SQLException
	{		
		internalPanel 	= _panel;
		
		dbStatement 	= _stm;
		
		internalPanel.removeAll();
		
		configRadioButtons();
		
		configTable();
		
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
		radioButtonFlightTicket.setSelected(true);
		radioButtonFlightTicket.setBounds(200, 20,
										  100, 40);
		
		radioButtonFlightTicket.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
			}
		});
		
		// Settings for the Accommodation Radio Button
		radioButtonAccommodation.setBounds(350, 20,
									  	   150, 40);
		
		radioButtonAccommodation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
			}
		});

		// Settings for the Package Radio Button
		radioButtonPackage.setBounds(500, 20,
									 150, 40);
		
		radioButtonPackage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
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
}
