package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import extras.LimitLength;
import repository.provider;

public class commonContactScreen extends JPanel {

	provider provider = new provider();

	public commonContactScreen(String userId) {
		setSize(994, 641);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JTextArea area = new JTextArea();
		area.setBorder(null);
		area.setFont(new Font("Cambria", Font.PLAIN, 12));
		area.setBounds(10, 193, 246, 200);
		area.setLineWrap(true);
		area.setBackground(new Color(255, 255, 255));
		area.setDocument(new LimitLength(500));
		JScrollPane pane = new JScrollPane(area);
		pane.setBorder(null);
		pane.setBounds(406, 288, 250, 110);

		add(pane);

		JLabel iconFeedback = new JLabel(provider.feedback);
		iconFeedback.setForeground(new Color(255, 255, 204));
		iconFeedback.setFont(new Font("Tahoma", Font.BOLD, 11));
		iconFeedback.setBounds(353, 290, 30, 30);
		add(iconFeedback);

		JLabel lblNewLabel_1 = new JLabel("<html>Character length must be less than 500.</html>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(51, 204, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(435, 409, 221, 15);
		add(lblNewLabel_1);

		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(0, 153, 153));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(406, 459, 250, 30);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (area.getText().trim().isEmpty()) {
						throw new Exception("Dear User, Please provider feedback before submitting.");
					}else {
						String query="insert into feedback values(?,?,?)";
						PreparedStatement feeds=provider.getdBConnection().prepareStatement(query);
						feeds.setString(1, userId);
						feeds.setString(2,area.getText().trim());
						feeds.setString(3, provider.getCurrentDateTime());
						feeds.execute();
						area.setText("");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(area, e2.getMessage());
				}finally {
					provider.CloseConnection();
				}
			}
		});
		add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("9876543210\r\n");
		lblNewLabel_3.setForeground(new Color(255, 255, 204));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(409, 126, 142, 30);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("evento@bangalore.com");
		lblNewLabel_4.setForeground(new Color(255, 255, 204));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(409, 167, 177, 30);
		add(lblNewLabel_4);

		JLabel lblNewLabel_6 = new JLabel(
				"<html>#11/3,8th Block, 5th Main, 7th Cross, Jayanagar, Bangalore, Karnataka.</html>");
		lblNewLabel_6.setForeground(new Color(255, 255, 204));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(409, 217, 200, 60);
		add(lblNewLabel_6);

		JLabel iconPhone = new JLabel(provider.whitePhone);
		iconPhone.setBounds(353, 126, 30, 30);
		add(iconPhone);

		JLabel iconMail = new JLabel(provider.email);
		iconMail.setBounds(353, 168, 30, 30);
		add(iconMail);

		JLabel iconLocation = new JLabel(provider.location);
		iconLocation.setBounds(353, 217, 30, 30);
		add(iconLocation);

	}

}
