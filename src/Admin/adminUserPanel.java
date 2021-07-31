package Admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JPanel;

import repository.provider;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class adminUserPanel extends JPanel{
	private JPanel panel;
	private JLabel userTypeAll,userTypeAdmin,userTypeCustomer,userTypePartner;
	Color selectionColor = new Color(0, 204, 153);
	Color unSelectedColor = new Color(51, 102, 153);
	private JScrollPane scrollPane;
	private JTable usersTable;
	provider provider = new provider();
	
	public adminUserPanel() {	
		setBackground(Color.DARK_GRAY);
		setBounds(0,0, 994, 641);
		setLayout(null);
		
		try {
			initComponents();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(panel, e.getMessage());
		}
	}
	
	private void initComponents() throws ClassNotFoundException, SQLException {
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(10, 10, 355, 33);
		add(panel);
		panel.setLayout(null);

		userTypeAll = new JLabel("ALL");
		userTypeAll.setFont(new Font("Courier New", Font.BOLD, 11));
		userTypeAll.setForeground(new Color(255, 255, 255));
		userTypeAll.setBackground(selectionColor);
		userTypeAll.setOpaque(true);
		userTypeAll.setCursor(provider.cursor);
		userTypeAll.setHorizontalAlignment(SwingConstants.CENTER);
		userTypeAll.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selection("all");
				try {
					reloadTable("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		userTypeAll.setBounds(0, 0, 60, 33);
		panel.add(userTypeAll);

		userTypeAdmin = new JLabel("ADMIN");
		userTypeAdmin.setFont(new Font("Courier New", Font.BOLD, 11));
		userTypeAdmin.setForeground(new Color(255, 255, 255));
		userTypeAdmin.setBackground(unSelectedColor);
		userTypeAdmin.setOpaque(true);
		userTypeAdmin.setCursor(provider.cursor);
		userTypeAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		userTypeAdmin.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selection("admin");
				try {
					reloadTable("admin");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}

		});
		userTypeAdmin.setBounds(60, 0, 75, 33);
		panel.add(userTypeAdmin);

		userTypeCustomer = new JLabel("CUSTOMER");
		userTypeCustomer.setFont(new Font("Courier New", Font.BOLD, 11));
		userTypeCustomer.setForeground(new Color(255, 255, 255));
		userTypeCustomer.setBackground(unSelectedColor);
		userTypeCustomer.setCursor(provider.cursor);
		userTypeCustomer.setOpaque(true);
		userTypeCustomer.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selection("customer");
				try {
					reloadTable("customer");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		userTypeCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		userTypeCustomer.setBounds(135, 0, 110, 33);
		panel.add(userTypeCustomer);

		userTypePartner = new JLabel("PARTNER");
		userTypePartner.setFont(new Font("Courier New", Font.BOLD, 11));
		userTypePartner.setForeground(new Color(255, 255, 255));
		userTypePartner.setBackground(unSelectedColor);
		userTypePartner.setCursor(provider.cursor);
		userTypePartner.setOpaque(true);
		userTypePartner.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selection("partner");
				try {
					reloadTable("partner");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}

		});
		userTypePartner.setHorizontalAlignment(SwingConstants.CENTER);
		userTypePartner.setBounds(245, 0, 110, 33);
		panel.add(userTypePartner);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 974, 576);
		add(scrollPane);

		usersTable = new JTable() {
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		usersTable.setBounds(0, 0, 974, 576);

		tableData(provider.selectQuery(provider.getdBConnection(),
				"SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE l.login_id=u.login_id"));

		usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setViewportView(usersTable);
	}
	
	private void tableData(ResultSet set) throws SQLException{
		DefaultTableModel table_model = new DefaultTableModel(new String[] { "Name", "Email", "Date of Joining" }, 0);
		while (set.next()) {
			table_model.addRow(new Object[] { set.getString(2), set.getString(1), set.getString(3) });
		}
		usersTable.setModel(table_model);
		usersTable.setRowSelectionAllowed(true);
	}
	
	private void reloadTable(String userType)throws ClassNotFoundException, SQLException {
		String query = new String();
		if (userType.equals(""))
			query = "SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE l.login_id=u.login_id";
		else
			query = "SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE l.login_id=u.login_id AND l.type='"
					+ userType + "'";

		tableData(provider.selectQuery(provider.getdBConnection(), query));
	}
		
	private void selection(String btn) {
		userTypeAll.setBackground(btn.equals("all")? selectionColor : unSelectedColor);
		userTypeAdmin.setBackground(btn.equals("admin")? selectionColor : unSelectedColor);
		userTypeCustomer.setBackground(btn.equals("customer")? selectionColor : unSelectedColor);
		userTypePartner.setBackground(btn.equals("partner")? selectionColor : unSelectedColor);
	}
}

/*
public class adminUserPanel extends JPanel {

	provider provider = new provider();
	private JTable table;
	DefaultTableModel table_model;
	ResultSet set;
	JPanel userView;

	public adminUserPanel() {
		setBounds(0, 89, provider.getScreenWidth(), provider.getScreenHeight() - 150);
		setLayout(null);

		try {
			initComponents();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(table, e.getMessage());
		}
	}

	public void initComponents() throws ClassNotFoundException, SQLException {
		userView = new JPanel();
		userView.setBounds(0, 0, 1366, 618);
		add(userView);
		userView.setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableScrollPane.setBounds(23, 107, 1333, 486);
		userView.add(tableScrollPane);

		table = new JTable(){
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		table.setBounds(0, 0, 1096, 200);
		tableData(provider.query(provider.getdBConnection(),
				"SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE l.login_id=u.login_id"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableScrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 33, 67, 26);
		userView.add(lblNewLabel);

		List<String> comboList = new ArrayList<String>();
		comboList.clear();
		comboList.add("Select Filter");
		comboList.add("admin");
		comboList.add("customer");
		comboList.add("partner");

		JComboBox comboBox = new JComboBox(comboList.toArray());
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox box = (JComboBox) e.getSource();
				try {
					String query = new String();
					if (box.getSelectedItem() == "Select Filter") {
						query = "SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE l.login_id=u.login_id";
						tableData(provider.query(provider.getdBConnection(), query));
					} else {
						query ="SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE l.login_id=u.login_id AND l.type='"+box.getSelectedItem()+"'";
						tableData(provider.query(provider.getdBConnection(), query));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					provider.CloseConnection();
				}
			}
		});
		comboBox.setBounds(100, 36, 337, 22);
		userView.add(comboBox);
	}

	public void tableData(ResultSet set) {
		table_model = new DefaultTableModel(new String[] { "Name", "Email", "Date of Joining" }, 0);
		try {
			while (set.next()) {
				table_model.addRow(new Object[] { set.getString(2), set.getString(1), set.getString(3)});
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(userView, e.getMessage());
		} finally {
			provider.CloseConnection();
		}
		table.setModel(table_model);
		table.setRowSelectionAllowed(true);
	}
}
*/