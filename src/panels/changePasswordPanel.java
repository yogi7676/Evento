package panels;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.mysql.cj.protocol.Resultset;

import repository.provider;

import java.awt.Color;

public class changePasswordPanel extends JPanel {
	private JPasswordField newPassField;
	private JPasswordField reEnterField;
	provider provider = new provider();

	public changePasswordPanel(String userID) {
		setBackground(new Color(255, 255, 255));
		setSize(400, 220);
		setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"<html>Dear User, Do not use previous password as your new password. Try creating strong password.</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 380, 35);
		add(lblNewLabel);

		JLabel iconPass = new JLabel(provider.password);
		iconPass.setHorizontalAlignment(SwingConstants.CENTER);
		iconPass.setBounds(10, 78, 30, 30);
		add(iconPass);

		JLabel iconRePass = new JLabel(provider.password);
		iconRePass.setHorizontalAlignment(SwingConstants.CENTER);
		iconRePass.setBounds(10, 127, 30, 30);
		add(iconRePass);

		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(45, 78, 98, 30);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Re-Enter");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(45, 127, 98, 30);
		add(lblNewLabel_2);

		newPassField = new JPasswordField();
		newPassField.setBounds(161, 78, 200, 30);
		add(newPassField);
		newPassField.setColumns(10);

		reEnterField = new JPasswordField();
		reEnterField.setBounds(162, 127, 200, 30);
		add(reEnterField);
		reEnterField.setColumns(10);

		JButton doneBtn = new JButton("Done");
		doneBtn.setForeground(new Color(255, 255, 255));
		doneBtn.setBackground(new Color(0, 153, 153));
		doneBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		doneBtn.setBorder(null);
		doneBtn.setBounds(161, 168, 200, 30);
		doneBtn.setCursor(provider.cursor);
		doneBtn.setContentAreaFilled(false);
		doneBtn.setOpaque(true);
		doneBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String pass, repass;
					pass = newPassField.getText().trim();
					repass = reEnterField.getText().trim();
					if (pass.isEmpty() || repass.isEmpty()) {
						throw new Exception("Please enter password.");
					} else if (pass.length() < 8) {
						throw new Exception("Enter a 8 Digit password.");
					} else if (!pass.equals(repass)) {
						newPassField.setText("");
						reEnterField.setText("");
						throw new Exception("Dear User, Passwords entered don not match.");
					} else {
						ResultSet set = provider.selectQuery(provider.getdBConnection(),
								" SELECT l.password FROM login l,users u WHERE u.user_id='" + userID.toUpperCase()
										+ "' AND l.login_id=u.login_id");
						if (set.next()) {
							if (set.getString(1).equals(pass)) {
								throw new Exception("Dear User,Do not use old passwords.");
							} else {
								update(pass, userID);
							}
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(newPassField, e2.getMessage());
				} finally {
					provider.CloseConnection();
				}
			}
		});
		add(doneBtn);
	}

	private void update(String newPass, String userId) throws ClassNotFoundException, SQLException {
		PreparedStatement updateValue = provider.updateValue(provider.getdBConnection(),
				"UPDATE login SET PASSWORD=? WHERE login_id=(SELECT login_id FROM users WHERE user_id=?)");
		updateValue.setString(1, newPass);
		updateValue.setString(2, userId);
		updateValue.execute();
		newPassField.setText("");
		reEnterField.setText("");
		new commonAccountScreen(userId).populate(userId);
	}
}
