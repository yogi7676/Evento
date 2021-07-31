package CustomerPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import repository.provider;

public class customerViewPanel extends JPanel {
	provider provider=new provider();
	private JTable table;
	List<String> selectedList = new ArrayList<String>();
	ResultSet set;
	DefaultTableModel table_model;

	public customerViewPanel() {
		setSize(994, 641);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		try {
			userView();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void userView() throws ClassNotFoundException, SQLException {
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setBounds(10, 107,956, 503);
		tableScrollPane.getViewport().setBackground(Color.white); 
		add(tableScrollPane);

		table = new JTable(){
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		
		table.setBounds(0, 0, 1096, 200);
		tableData(provider.selectQuery(provider.getdBConnection(), "select * from collaborators"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableScrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 37, 67, 26);
		add(lblNewLabel);

		List<String> comboList = new ArrayList<String>();
		comboList.clear();
		comboList.add("Select Filter");
		for (String i : provider.getEventsList())
			comboList.add(i);
		JComboBox comboBox = new JComboBox(comboList.toArray());
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox box = (JComboBox) e.getSource();
				try {
					String query = new String();
					if (box.getSelectedItem() == "Select Filter") {
						query = "select *from collaborators";
						tableData(provider.selectQuery(provider.getdBConnection(), query));
					}else {
						query = "SELECT * FROM collaborators WHERE json_extract(compatibility,'$." + "\""
								+ box.getSelectedItem() + "\"" + "'" + ")=true";
						tableData(provider.selectQuery(provider.getdBConnection(), query));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					provider.CloseConnection();
				}
			}
		});
		comboBox.setBounds(87, 40, 337, 22);
		add(comboBox);
	}

	
	public void tableData(ResultSet set) {
		table_model = new DefaultTableModel(new String[] {"ID", "Company Name", "Address", "Phone", "Email" }, 0);
		try {
			while (set.next()) {
				table_model.addRow(
						new Object[] {set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5) });
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(table, e.getMessage());
		} finally {
			provider.CloseConnection();
		}
		table.setModel(table_model);
		table.setRowSelectionAllowed(true);
	}

}
