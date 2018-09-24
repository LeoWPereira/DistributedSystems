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

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
	 * @brief	Member to store the group of Radio Buttons
	 */
	private static ButtonGroup 	group;
	
	/**
	 * @brief	Member containing the info about the "City" Radio Button
	 */
	private static JRadioButton	radioCity;
	
	/**
	 * @brief	Member containing the info about the "Hotel" Radio Button
	 */
	private static JRadioButton	radioHotel;
	
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
	 * @brief	Default Constructor
	 * 
	 * This constructor will first remove everything from the JPanel
	 * 
	 * @param	panel	-	JPanel containing this panel future info
	 */
	public HotelsPanel(JPanel panel)
	{
		internalPanel = panel;
		
		internalPanel.removeAll();
		
		configRadioButtons();
		
		configStateAndCities();
		
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
		group 		= new ButtonGroup();
		
		radioCity 	= new JRadioButton("Busca por Cidade");
		radioHotel	= new JRadioButton("Busca por Hotel");
		
		// Settings for the City Radio Button
		radioCity.setSelected(true);
		radioCity.setBounds(225, 5,
							150, 40);
		
		radioCity.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelTown.setVisible(true);

				comboBoxState.setVisible(true);
				
				comboBoxCity.setVisible(true);
				
				labelHotel.setVisible(false);
				
				textFieldHotel.setVisible(false);
			}
		});
		
		// Settings for the Hotel Radio Button
		radioHotel.setBounds(425, 5,
							 150, 40);
		
		radioHotel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelTown.setVisible(false);

				comboBoxState.setVisible(false);
				
				comboBoxCity.setVisible(false);
				
				labelHotel.setVisible(true);
				
				textFieldHotel.setVisible(true);
			}
		});
		
        group.add(radioCity);
        group.add(radioHotel);
        
        internalPanel.add(radioCity);
        internalPanel.add(radioHotel);
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
				               180, 20);
				
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
		labelHotel.setVisible(false);
		labelHotel.setPreferredSize(new Dimension(75, 15));
		labelHotel.setBounds(10, 80,
						     120, 20);
		
		// Hotel Name Text Field configurations
		textFieldHotel.setVisible(false);
		textFieldHotel.setPreferredSize(new Dimension(75, 15));
		textFieldHotel.setBounds(120, 80,
						         220, 20);
				
		internalPanel.add(labelHotel);
		internalPanel.add(textFieldHotel);
	}
	
	/**
	 * @brief	This method will handle the initial configurations to the Search Button
	 */
	public void configButton()
	{
		buttonSearch = new JButton("Buscar Hospedagem");
		
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
					//TODO Do action 'search'
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
		
		if(radioCity.isSelected())
		{
			if(comboBoxState.getSelectedItem().equals("  ")	||
			   comboBoxCity.getSelectedItem().equals("  "))
			{
				returnValue = true;
			}
		}
		else
		{
			if(textFieldHotel.getText().equals(""))
			{
				returnValue = true;
			}
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
		table.setPreferredSize(new Dimension(244, 300));
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
			new String[] {"Cidade", "Hotel", "Preço (R$)"})
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
		table.getModel().setValueAt(value, 
									row, 
									column);
	}
}
