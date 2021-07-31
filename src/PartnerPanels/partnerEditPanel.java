package PartnerPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import com.mysql.cj.protocol.Resultset;

import extras.LimitLength;
import panels.commonViewPanel;
import repository.provider;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class partnerEditPanel extends JPanel {
	provider provider = new provider();
	JLabel searchLbl;
	private JTextField textField, cidTextField;
	Color selectionColor = new Color(204, 102, 255);
	Color unSelectedColor = new Color(102, 0, 153);
	JLabel updateEmail, updatePhone, updateAddress, icon, label;
	JScrollPane scrollPane;
	JButton doneBtn;
	JTextArea textArea;

	public partnerEditPanel(String userId) {
		setSize(994, 641);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Collaboration ID :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel.setBounds(500, 11, 116, 30);
		add(lblNewLabel);

		cidTextField = new JTextField();
		cidTextField.setBounds(626, 11, 152, 30);
		add(cidTextField);
		cidTextField.setColumns(10);

		searchLbl = new JLabel(provider.search);
		searchLbl.setToolTipText("Click to fetch Data.");
		searchLbl.setHorizontalAlignment(SwingConstants.CENTER);
		searchLbl.setCursor(provider.cursor);
		searchLbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String id=cidTextField.getText().trim();
				try {
					if (id.isEmpty())
						throw new Exception("Please enter collaboration id");
					else {
						ResultSet set = provider.selectQuery(provider.getdBConnection(),
								"select " + getFieldName() + " from collaborators where userid='"+userId+ "' and col_id='"
										+ id.toUpperCase() + "'");
						if (set.next()) {
							if (label.getText().equals("Address"))
								textArea.setText(set.getString(1));
							else
								textField.setText(set.getString(1));
						} else {
							throw new Exception("Enter valid Collaboration ID");
						}
					}
				} catch (Exception error) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(textArea, error.getMessage());
				} finally {

				}

			}
		});
		searchLbl.setBounds(793, 11, 30, 30);
		add(searchLbl);

		JLabel lblNewLabel_1 = new JLabel("Update");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 11, 46, 30);
		add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 153));
		panel.setBounds(76, 11, 170, 30);
		add(panel);
		panel.setLayout(null);

		updateEmail = new JLabel("Email");
		updateEmail.setHorizontalAlignment(SwingConstants.CENTER);
		updateEmail.setBounds(0, 0, 46, 30);
		updateEmail.setOpaque(true);
		updateEmail.setCursor(provider.cursor);
		updateEmail.setBackground(selectionColor);
		updateEmail.setForeground(Color.white);
		updateEmail.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				populateView("email");
			}

		});
		panel.add(updateEmail);

		updatePhone = new JLabel("Phone");
		updatePhone.setBackground(unSelectedColor);
		updatePhone.setHorizontalAlignment(SwingConstants.CENTER);
		updatePhone.setBounds(56, 0, 46, 30);
		updatePhone.setCursor(provider.cursor);
		updatePhone.setOpaque(true);
		updatePhone.setForeground(Color.white);
		updatePhone.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				populateView("phone");
			}

		});
		panel.add(updatePhone);

		updateAddress = new JLabel("Address");
		updateAddress.setHorizontalAlignment(SwingConstants.CENTER);
		updateAddress.setOpaque(true);
		updateAddress.setForeground(Color.white);
		updateAddress.setBounds(112, 0, 58, 30);
		updateAddress.setBackground(new Color(102, 0, 153));
		updateAddress.setCursor(provider.cursor);
		updateAddress.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				populateView("address");
			}

		});
		panel.add(updateAddress);

		initComponents(userId);
		populateView("email");
	}

	private void initComponents(String userId) {
		icon = new JLabel();
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setBounds(303, 200, 25, 25);
		add(icon);

		textField = new JTextField();
		textField.setBounds(408, 200, 200, 25);
		add(textField);
		textField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 200, 200, 100);
		add(scrollPane);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setDocument(new LimitLength(300));
		scrollPane.setViewportView(textArea);

		label = new JLabel();
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Cambria", Font.BOLD, 14));
		label.setBounds(342, 200, 63, 25);
		add(label);

		doneBtn = new JButton("Update");
		doneBtn.setBackground(unSelectedColor);
		doneBtn.setBorder(null);
		doneBtn.setForeground(Color.white);
		doneBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (cidTextField.getText().trim().isEmpty())
						throw new Exception("Please enter Collaboration ID");
					else {
						switch (label.getText()) {
						case "Email":
							if (textField.getText().trim().isEmpty())
								throw new Exception("Enter email address");
							else if (provider.validateEmail(textField.getText().trim()) == false)
								throw new Exception("Enter valid email address");
							else {
								update(getFieldName(), textField.getText().trim(),cidTextField.getText().trim());
							}

							break;
						case "Phone":
							if (textField.getText().trim().isEmpty())
								throw new Exception("Enter Phone Number");
							else if (provider.validatePhone(textField.getText().trim()) == false)
								throw new Exception("Enter valid Phone Number");
							else {
								update(getFieldName(), textField.getText().trim(),cidTextField.getText().trim());
							}
							break;
						case "Address":
							if (textArea.getText().trim().isEmpty())
								throw new Exception("Please fill in your address");
							else
								update(getFieldName(), textField.getText().trim(),cidTextField.getText().trim());

							break;
						}
					}
					String query = "select *from collaborators where userid='" + userId + "'";
					new commonViewPanel(query, "partner").populateView(0, query);

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(textArea, e2.getMessage());
				} finally {
					provider.CloseConnection();
				}
			}
		});
		doneBtn.setCursor(provider.cursor);
		add(doneBtn);
	}

	private void update(String field, String value,String col_id) throws ClassNotFoundException, SQLException {
		PreparedStatement updateValue = provider.updateValue(provider.getdBConnection(),
				"update collaborators set " + field + "=? where col_id=?");
		updateValue.setString(1, value);
		updateValue.setString(2, col_id);
		updateValue.execute();
		cidTextField.setText("");
		textArea.setText("");
		textField.setText("");
	}

	private void populateView(String selection) {
		updateEmail.setBackground(selection.equals("email") ? selectionColor : unSelectedColor);
		updatePhone.setBackground(selection.equals("phone") ? selectionColor : unSelectedColor);
		updateAddress.setBackground(selection.equals("address") ? selectionColor : unSelectedColor);
		if (selection.equals("address")) {
			doneBtn.setBounds(408, 310, 200, 23);
			textareaVisibility(false);
		} else {
			textareaVisibility(true);
			doneBtn.setBounds(408, 234, 200, 23);
		}
		switch (selection) {
		case "email":
			set("Email", provider.email);
			break;
		case "phone":
			set("Phone", provider.whitePhone);
			break;
		case "address":

			set("Address", provider.location);
			break;
		}
	}

	private void textareaVisibility(Boolean visible) {
		scrollPane.setVisible(!visible);
		textField.setVisible(visible);
	}

	private void set(String sname, ImageIcon sicon) {
		icon.setIcon(sicon);
		label.setText(sname);
		textArea.setText("");
		textField.setText("");
	}

	private String getFieldName() {
		String select = new String();
		switch (label.getText()) {
		case "Email":
			select = "col_email";
			break;
		case "Phone":
			select = "col_phone";
			break;
		case "Address":
			select = "col_address";
			break;
		}
		return select;
	}
}
