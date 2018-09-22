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

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Extra.CitiesBrazil;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PassagesPanel extends JPanel
{
	/**
	 * @brief
	 */
	private static final long 	serialVersionUID = 1311564096576338404L;

	/**
	 * @brief
	 */
	private static JPanel 		internalPanel;
	
	/**
	 * @brief
	 */
	private static ButtonGroup 	group;
	
	/**
	 * @brief
	 */
	private static JRadioButton	radioButtonOneWay;
	
	/**
	 * @brief
	 */
	private static JRadioButton	radioButtonRoundWay;
	
	/**
	 * @brief
	 */
	private static CitiesBrazil brazil;
	
	/**
	 * @brief
	 */
	private JComboBox<String> comboBoxEstadosSrc;
	
	/**
	 * @brief
	 */
	private JComboBox<String> comboBoxCidadesSrc;
	
	/**
	 * @brief
	 */
	private JComboBox<String> comboBoxEstadosDest;
	
	/**
	 * @brief
	 */
	private JComboBox<String> comboBoxCidadesDest;
	
	/**
	 * @brief
	 */
	private JTable 			  table;
	
	/**
	 * @brief
	 */
	private JScrollPane 	  scrollPaneTabela;
	
	/**
	 * @brief
	 */
	private JButton 		  buttonSearch;
	
	/**
	 * @brief
	 */
	private JLabel 			  labelDateOneWay;
	
	/**
	 * @brief
	 */
	private JLabel 		  	  labelDateRoundTrip;
	
	/**
	 * @brief
	 */
	private JDatePickerImpl   datePickerOneWayTrip;
	
	/**
	 * @brief
	 */
	private JDatePickerImpl   datePickerRoundTrip;
	
	/**
	 * @brief
	 * 
	 * @param	panel	-
	 */
	public PassagesPanel(JPanel panel)
	{
		internalPanel = panel;
		
		configRadioButtons();
		
		configEstadoAndCidade();
		
		configDates();
		
		configButton();
		
		configTable();
		
		internalPanel.updateUI();
	}
	
	/**
	 * @brief
	 */
	public void configRadioButtons()
	{
		group 				= new ButtonGroup();
		
		radioButtonOneWay 	= new JRadioButton("Somente Ida");
		radioButtonRoundWay	= new JRadioButton("Ida / Volta");
		
		radioButtonOneWay.setSelected(true);
		radioButtonOneWay.setBounds(225, 5,
									100, 40);
		
		radioButtonOneWay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelDateRoundTrip.setVisible(false);
			}
		});
		
		radioButtonRoundWay.setBounds(425, 5,
									  100, 40);
		
		radioButtonRoundWay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				labelDateRoundTrip.setVisible(true);
			}
		});
		
        group.add(radioButtonOneWay);
        group.add(radioButtonRoundWay);
        
        internalPanel.add(radioButtonOneWay);
        internalPanel.add(radioButtonRoundWay);
	}
	
	/**
	 * @brief
	 */
	public void configEstadoAndCidade()
	{
		brazil 					= new CitiesBrazil();
		
		comboBoxEstadosSrc 		= new JComboBox<String>();
		comboBoxCidadesSrc		= new JComboBox<String>();
		comboBoxEstadosDest		= new JComboBox<String>();
		comboBoxCidadesDest		= new JComboBox<String>();
		
		JLabel labelSrc			= new JLabel("Origem: ");
		JLabel labelDest		= new JLabel("Destino: ");
		
		
		// Passages label configurations
		labelSrc.setPreferredSize(new Dimension(75, 15));
		labelSrc.setBounds(10, 80,
						   85, 20);
		
		// Hotel Label configurations
		labelDest.setPreferredSize(new Dimension(80, 15));
		labelDest.setBounds(10, 130,
							85, 20);
		
		// 
		comboBoxEstadosSrc.setModel(new DefaultComboBoxModel<String>(brazil.getEstados()));
		comboBoxEstadosSrc.setBounds(70, 80, 
								     60, 20);
		
		comboBoxEstadosSrc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				changeCitiesComboBox(comboBoxCidadesSrc,
									 comboBoxEstadosSrc.getItemAt(comboBoxEstadosSrc.getSelectedIndex()));
			}
		});
		
		// 
		comboBoxCidadesSrc.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxEstadosSrc.getItemAt(comboBoxEstadosSrc.getSelectedIndex()))));
		comboBoxCidadesSrc.setBounds(160, 80, 
				                     180, 20);
		
		// 
		comboBoxEstadosDest.setModel(new DefaultComboBoxModel<String>(brazil.getEstados()));
		comboBoxEstadosDest.setBounds(70, 130, 
						   		      60, 20);
		
		comboBoxEstadosDest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				changeCitiesComboBox(comboBoxCidadesDest,
									 comboBoxEstadosDest.getItemAt(comboBoxEstadosDest.getSelectedIndex()));
			}
		});
		
		// 
		comboBoxCidadesDest.setModel(new DefaultComboBoxModel<String>(brazil.getCities(comboBoxEstadosSrc.getItemAt(comboBoxEstadosSrc.getSelectedIndex()))));
		comboBoxCidadesDest.setBounds(160, 130, 
				                      180, 20);
		
		internalPanel.add(labelSrc);
		internalPanel.add(labelDest);
		internalPanel.add(comboBoxEstadosSrc);
		internalPanel.add(comboBoxCidadesSrc);
		internalPanel.add(comboBoxEstadosDest);
		internalPanel.add(comboBoxCidadesDest);
	}
	
	/**
	 * @brief
	 * 
	 * @param	comboBox	-
	 * @param	index		-
	 */
	public void changeCitiesComboBox(JComboBox<String>	comboBox,
									 String				index)
	{
		comboBox.setModel(new DefaultComboBoxModel<String>(brazil.getCities(index)));
	}
	
	/**
	 * @brief
	 */
	@SuppressWarnings("serial")
	public void configTable()
	{
		table 				= new JTable();
		scrollPaneTabela 	= new JScrollPane();
		
		//
		scrollPaneTabela.setBackground(new Color(222, 184, 135));
		scrollPaneTabela.setBorder(null);
		scrollPaneTabela.setViewportView(table);
		scrollPaneTabela.setBounds(370, 80,
								   400, 321);
		
		//
		table.setShowGrid(false);
		//table.setBackground(new Color(210, 180, 140));
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
			new String[] {"Origem", "Destino", "Data", "Preço (R$)"})
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
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.setAutoCreateRowSorter(true);
		
		internalPanel.add(scrollPaneTabela);
	}
	
	public void insertTableField(int 	row,
								 int 	column,
								 String	value)
	{
		table.getModel().setValueAt(value, 
									row, 
									column);
	}
	
	public void configButton()
	{
		buttonSearch = new JButton("Buscar Passagens");
		
		buttonSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		buttonSearch.setBackground(new Color(238, 238, 238));
		buttonSearch.setBounds(10, 370,
							   350, 30);
		
		buttonSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		internalPanel.add(buttonSearch);
	}
	
	public void configDates()
	{
		labelDateOneWay		= new JLabel("Data de Ida: ");
		labelDateRoundTrip	= new JLabel("Data de Retorno: ");
		
		// 
		labelDateOneWay.setPreferredSize(new Dimension(75, 15));
		labelDateOneWay.setBounds(10, 200,
						          120, 20);
		
		// 
		labelDateRoundTrip.setVisible(false);
		labelDateRoundTrip.setPreferredSize(new Dimension(80, 15));
		labelDateRoundTrip.setBounds(10, 250,
							         120, 20);
		
		internalPanel.add(labelDateOneWay);
		internalPanel.add(labelDateRoundTrip);
		
		UtilDateModel  model 		= new UtilDateModel();
		JDatePanelImpl datePanel 	= new JDatePanelImpl(model);
		
		datePickerOneWayTrip 		= new JDatePickerImpl(datePanel);
		 
		datePickerOneWayTrip.setBounds(140, 197, 
									   200, 40);
		
		internalPanel.add(datePickerOneWayTrip);
	}
}
