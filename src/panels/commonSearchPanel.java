package panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import extras.TheTableModel;
import model.collaboratorsModel;
import repository.provider;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class commonSearchPanel extends JPanel {
	private JTextField textField;
	provider provider = new provider();
	JPanel searchPanel, filterPanel;
	String usertype;
	Color selectionColor = new Color(0, 204, 153);
	Color unSelectedColor = new Color(51, 102, 153);
	JLabel searchCollaborations, searchUsers;
	String selectedSearch="collaborations";
	private JTable Usertable,CollaborationTable;
	JScrollPane UserscrollPane,CollaborationScrollPane;

	public commonSearchPanel(String usertype) {
		this.usertype = usertype;
		setSize(994, 641);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		initComponents();

	}

	private void initComponents() {
		searchPanel();
		filterPanel();
		UserscrollPane=new JScrollPane();
		CollaborationScrollPane=new JScrollPane();
	}

	private void searchPanel() {
		searchPanel = new JPanel();
		searchPanel.setBackground(Color.DARK_GRAY);
		if (usertype.equals("admin")) {
			searchPanel.setBounds(640, 20, 300, 30);
		} else {
			searchPanel.setBounds(20, 20, 300, 30);
		}

		add(searchPanel);
		searchPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 54, 30);
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 11));
		searchPanel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 204));

		textField = new JTextField();
		textField.setBounds(57, -1, 200, 30);
		searchPanel.add(textField);
		textField.setColumns(10);

		JLabel iconSearch = new JLabel(provider.search);
		iconSearch.setBounds(267, 5, 33, 20);
		searchPanel.add(iconSearch);
		iconSearch.setCursor(provider.cursor);
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				try {
					String id=textField.getText().trim().toUpperCase();
					if(id.isEmpty()) {
						throw new Exception(selectedSearch.equals("collaborations")? "Enter a valid Collaboration ID" : "Enter a valid User ID");
					}else {
						populateTable(id);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(iconSearch,e2.getMessage());
				}
			}

		});

	}

	private void filterPanel() {
		filterPanel = new JPanel();
		filterPanel.setBackground(Color.DARK_GRAY);
		if (usertype.equals("admin")) {
			filterPanel.setBounds(20, 20, 250, 30);
			filterPanel.setVisible(true);
		} else {
			filterPanel.setVisible(false);
		}
		filterPanel.setBounds(20, 20, 250, 30);
		filterPanel.setLayout(null);
		add(filterPanel);

		JLabel lblNewLabel_1 = new JLabel("Filter");
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 89, 30);
		lblNewLabel_1.setFont(new Font("Courier New", Font.BOLD, 12));
		filterPanel.add(lblNewLabel_1);

		searchCollaborations = new JLabel("Collaborations");
		searchCollaborations.setBackground(selectionColor);
		searchCollaborations.setBounds(89, 0, 105, 30);
		searchCollaborations.setOpaque(true);
		filterPanel.add(searchCollaborations);
		searchCollaborations.setHorizontalAlignment(SwingConstants.CENTER);
		searchCollaborations.setCursor(provider.cursor);
		searchCollaborations.setForeground(new Color(255, 255, 255));
		searchCollaborations.setFont(new Font("Courier New", Font.BOLD, 11));
		searchCollaborations.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selection("collaborations");
				UserscrollPane.setVisible(false);
				CollaborationScrollPane.setVisible(true);
			}

		});

		searchUsers = new JLabel("Users");
		searchUsers.setBackground(unSelectedColor);
		searchUsers.setBounds(194, 0, 56, 30);
		searchUsers.setOpaque(true);
		filterPanel.add(searchUsers);
		searchUsers.setHorizontalAlignment(SwingConstants.CENTER);
		searchUsers.setForeground(new Color(255, 255, 255));
		searchUsers.setFont(new Font("Courier New", Font.BOLD, 11));
		searchUsers.setCursor(provider.cursor);
		searchUsers.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selection("users");
				UserscrollPane.setVisible(true);
				CollaborationScrollPane.setVisible(false);
			}

		});
	}

	private void populateTable(String id) {
		//scrollPane.setVisible(false);
		try {
			switch(selectedSearch) {
			case "collaborations":
				viewForCollaborations(id);
				setVisibility(false);
				break;
			case "users":
				viewForUser(id);
				setVisibility(true);
				break;					
			}	
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(UserscrollPane, e.getMessage());
		}
	}
	
	private void setVisibility(Boolean visible) {
		UserscrollPane.setVisible(visible);
		CollaborationScrollPane.setVisible(!visible);
	}

	private void viewForUser(String userId) {
		//UserscrollPane = new JScrollPane();
		UserscrollPane.setBounds(20, 87, 949, 518);
		add(UserscrollPane);
		Usertable = new JTable() {
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		DefaultTableModel table_model = new DefaultTableModel(new String[] { "Name", "Email", "Date of Joining" }, 0);
		try {
			ResultSet set = provider.selectQuery(provider.getdBConnection(),
					" SELECT l.email,u.username,u.dateOfCreation FROM users u,login l WHERE u.user_id='"+userId+"' AND l.login_id=u.login_id");
			if(set.next()) {
				table_model.addRow(new Object[] { set.getString(2), set.getString(1), set.getString(3) });
			}else {
				throw new Exception("Enter a valid User ID");
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(UserscrollPane, e.getMessage());
		} finally {
			provider.CloseConnection();
		}
		Usertable.setModel(table_model);
		Usertable.setRowSelectionAllowed(true);
		UserscrollPane.setViewportView(Usertable);
	}

	private void viewForCollaborations(String col_id)
			throws Exception {
		//CollaborationScrollPane = new JScrollPane();
		CollaborationScrollPane.setBounds(20, 87, 949, 518);
		add(CollaborationScrollPane);
		CollaborationTable=new JTable() {
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};		
		ArrayList<collaboratorsModel> list = provider.bindTable(provider.getdBConnection(),
				"select * from collaborators where col_id='" + col_id + "'");
		if (list.size()==0)
			throw new Exception("Enter a valid Collaboration ID");
		else {
			String[] colNames = { "Company Name", "Email", "Phone Number", "Address", "Image" };
			Object[][] rows = new Object[list.size()][5];
			for (int i = 0; i < list.size(); i++) {
				rows[i][0] = list.get(i).getCompanyName();
				rows[i][1] = list.get(i).getEmail();
				rows[i][2] = list.get(i).getPhone();
				rows[i][3] = list.get(i).getAddress();
				ImageIcon icon = new ImageIcon(
						new ImageIcon(list.get(i).getImage()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
				rows[i][4] = icon;
			}
			TheTableModel model = new TheTableModel(rows, colNames);
			CollaborationTable.setModel(model);
			CollaborationTable.setRowHeight(120);
			CollaborationTable.getColumnModel().getColumn(4).setPreferredWidth(150);
			CollaborationScrollPane.setViewportView(CollaborationTable);
		}
		
	}

	private void selection(String btn) {
		searchCollaborations.setBackground(btn.equals("collaborations") ? selectionColor : unSelectedColor);
		searchUsers.setBackground(btn.equals("users") ? selectionColor : unSelectedColor);
		selectedSearch = btn;
	}
}
