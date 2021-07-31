package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JPanel;

import repository.TandC;
import repository.provider;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import CustomerPanels.Customer;
import PartnerPanels.Partner;
import model.modelUser;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class Register extends JFrame {
	private JPanel panel;
	private provider provider = new provider();
	private TandC terms = new TandC();
	private JTextField email, username;
	private JPasswordField password, repass;
	private JButton signupBtn;
	private JLabel lblSignup, lblUsername, lblPass, lblRepass, lblEmail;
	private JLabel lblTC;
	private JLabel lblClose, lblHaveAcc;
	private Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	private JLabel lblError;
	private JCheckBox registerTC;

	public Register(String userType) {
		setUndecorated(true);
		setTitle("Evento | Signup");
		setSize(750, 400);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);

		// components in left frame
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 204, 153));
		leftPanel.setBounds(0, 0, 303, 400);
		panel.add(leftPanel);
		leftPanel.setLayout(null);

		// label evento
		JLabel lblNewLabel = new JLabel("EVENTO");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		lblNewLabel.setBounds(10, 159, 283, 35);
		leftPanel.add(lblNewLabel);

		// label moto
		JLabel lblNewLabel_1 = new JLabel("We Manage, You Celebrate.");
		lblNewLabel_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 196, 283, 14);
		leftPanel.add(lblNewLabel_1);

		// components in right frame
		// label signup
		lblSignup = new JLabel("SIGNUP");
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setBackground(new Color(0, 0, 0));
		lblSignup.setForeground(new Color(51, 204, 204));
		lblSignup.setFont(new Font("Cambria Math", Font.BOLD, 24));
		lblSignup.setBounds(367, 36, 327, 35);
		panel.add(lblSignup);

		// label email
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblEmail.setBounds(367, 131, 46, 14);
		panel.add(lblEmail);

		// label pass
		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblPass.setBounds(367, 168, 67, 14);
		panel.add(lblPass);

		// textfield email
		email = new JTextField();
		lblEmail.setLabelFor(email);
		email.setBounds(444, 124, 250, 25);
		panel.add(email);
		email.setColumns(10);

		// password field
		password = new JPasswordField();
		password.setBounds(444, 161, 250, 25);
		panel.add(password);

		// signup button
		signupBtn = new JButton("Register");
		signupBtn.setCursor(cursor);
		signupBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					String strname, stremail, strpass, strrepass;
					strname = username.getText();
					stremail = email.getText();
					strpass = password.getText();
					strrepass = repass.getText();
					if (strname.length() == 0 || stremail.length() == 0 || strpass.length() == 0
							|| strrepass.length() == 0)
						throw new Exception("Fill in all the fields");
					else if (provider.validateEmail(stremail) == false) {
						email.setText("");
						email.requestFocus();
						throw new Exception("Valid email address required.");
					} else if (strpass.length() < 8) {
						password.setText("");
						password.requestFocus();
						throw new Exception("8-Digit password required.");
					} else if (!strpass.equals(strrepass)) {
						password.setText("");
						repass.setText("");
						password.requestFocus();
						throw new Exception("Passwords do not match");
					} else if (registerTC.isSelected() == false)
						throw new Exception("Please agree to terms and conditions");
					else {
						if (provider.checkEmailExists(provider.getdBConnection(), stremail)) {
							email.setText("");
							email.requestFocus();
							throw new Exception("Email id already exists.");
						} else {
							lblError.setText("");
							modelUser user = new modelUser(strname, stremail, strrepass, userType);
							String userId = provider.registerUser(provider.getdBConnection(), user);
							email.setText("");
							password.setText("");
							username.setText("");
							repass.setText("");
							registerTC.setSelected(false);
							switch (userType) {
							case "customer":
								Customer customer = new Customer(userId);
								customer.setVisible(true);
								break;
							case "partner":
								Partner partner = new Partner(userId);
								partner.setVisible(true);
								break;
							}
							dispose();
						}
					}

				} catch (Exception exception) {
					// TODO: handle exception
					lblError.setText(exception.getMessage());
					System.out.println(exception.getMessage());
				} finally {
					provider.CloseConnection();
				}
			}
		});
		signupBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		signupBtn.setForeground(new Color(255, 255, 255));
		signupBtn.setBorder(null);
		signupBtn.setBackground(new Color(51, 204, 204));
		signupBtn.setBounds(371, 302, 323, 23);
		panel.add(signupBtn);

		// label already have account
		lblHaveAcc = new JLabel("Have an Account ? Login");
		lblHaveAcc.setCursor(cursor);
		lblHaveAcc.setForeground(Color.BLUE);
		lblHaveAcc.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}

		});
		lblHaveAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblHaveAcc.setFont(new Font("Cambria", Font.PLAIN, 13));
		lblHaveAcc.setBounds(371, 366, 323, 23);
		panel.add(lblHaveAcc);

		// label username
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblUsername.setBounds(367, 95, 67, 14);
		panel.add(lblUsername);

		// username textfield
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(444, 88, 250, 25);
		panel.add(username);

		// label repass
		lblRepass = new JLabel("Re-Enter");
		lblRepass.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblRepass.setBounds(367, 207, 56, 14);
		panel.add(lblRepass);

		// repass textfield
		repass = new JPasswordField();
		repass.setBounds(444, 197, 250, 25);
		panel.add(repass);

		// label close
		lblClose = new JLabel("X");
		lblClose.setCursor(cursor);
		lblClose.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(NORMAL);
			}

		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setFont(new Font("Cambria", Font.BOLD, 16));
		lblClose.setBounds(722, 11, 18, 14);
		panel.add(lblClose);

		// label terms and conditions
		lblTC = new JLabel("<HTML><U>Read Terms and Conditions</U></HTML>");
		lblTC.setFont(new Font("Cambria", Font.PLAIN, 13));
		lblTC.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTextArea area = new JTextArea(terms.TermsandConditions());
				area.setLineWrap(true);
				area.setEditable(false);
				JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				JDialog dialog = new JDialog();
				dialog.setTitle("Terms and Conditions");
				dialog.getContentPane().add(pane);
				dialog.setSize(400, 250);
				dialog.setLocation(500, 200);
				dialog.setVisible(true);
			}

		});
		lblTC.setCursor(cursor);
		lblTC.setForeground(new Color(0, 51, 255));
		lblTC.setBounds(367, 243, 327, 23);
		panel.add(lblTC);

		// checkbox agree T&C
		registerTC = new JCheckBox("Agree T&C");
		registerTC.setBackground(new Color(255, 255, 255));
		registerTC.setBounds(367, 265, 115, 23);
		registerTC.setFont(new Font("Cambria", Font.PLAIN, 13));
		panel.add(registerTC);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(367, 336, 327, 14);
		panel.add(lblError);
	}

}
