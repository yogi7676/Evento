package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Admin.Admin;
import CustomerPanels.Customer;
import PartnerPanels.Partner;
import repository.provider;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame{
	private JPanel panel;
	provider provider=new provider();
	private JTextField email;
	private JPasswordField password;
	private JButton loginbtn;
	private JLabel lblSignin,lblEmail,lblPass;
	private JLabel lblCreateAccount,lblPartner;
	private JLabel lblClose, lblError;

	public Login() {
		setUndecorated(true);
		setTitle("Evento | Signin");
		setSize(750,400);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// left design panel
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 153));
		panel_1.setBounds(0, 0, 303, 400);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EVENTO");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 159, 283, 35);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("We Manage, You Celebrate.");
		lblNewLabel_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 196, 283, 14);
		panel_1.add(lblNewLabel_1);
		
		//contents on right
		// label signin
		lblSignin = new JLabel("SIGNIN");
		lblSignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignin.setBackground(new Color(0, 0, 0));
		lblSignin.setForeground(new Color(51, 204, 204));
		lblSignin.setFont(new Font("Cambria Math", Font.BOLD, 24));
		lblSignin.setBounds(394, 73, 250, 35);
		panel.add(lblSignin);
		
		// label email
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblEmail.setBounds(394, 131, 46, 14);
		panel.add(lblEmail);
		
		//label pass
		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblPass.setBounds(394, 198, 86, 14);
		panel.add(lblPass);
			
		// email textfield
		email = new JTextField();
		lblEmail.setLabelFor(email);
		email.setBounds(394, 156, 250, 25);
		panel.add(email);
		email.setColumns(10);
		
		// password textfield
		password = new JPasswordField();
		password.setBounds(394, 223, 250, 25);
		panel.add(password);
		
		// login button
		loginbtn = new JButton("LOGIN");
		loginbtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				loginbtn.setEnabled(false);
				try {
					String strEmail,strPass;
					strEmail=email.getText();
					strPass=password.getText();
					if(strEmail.length()==0 || strPass.length()==0) 
						throw new Exception("Email and Password required");
					else if(provider.validateEmail(strEmail)==false) {
						email.setText("");
						email.requestFocus();
						throw new Exception("Valid email required.");
					}else if(strPass.length()<8) {
						password.setText("");
						password.requestFocus();
						throw new Exception("8-Digit password required.");
					}else {
						ResultSet set=provider.loginUser(provider.getdBConnection(), strEmail, strPass);
						//ResultSet userid=provider.getUserId(provider.getdBConnection(), set.getString(1));
						if(set.next()) {
							String type=set.getString(4);
							lblError.setText("");
							email.setText("");
							password.setText("");
							switch(type) {
							case "admin":
								Admin admin=new Admin(set.getString(5));
								admin.setVisible(true);
								break;
							case "customer":
								Customer customer=new Customer(set.getString(5));
								customer.setVisible(true);
								break;
							case "partner":
								Partner partner=new Partner(set.getString(5));
								partner.setVisible(true);
								break;
								default:break;
							}
							dispose();
							
						}else {
							email.setText("");
							password.setText("");
							throw new Exception("User not found.");
						}
							
					}
					
				} catch (Exception exception) {
					// TODO: handle exception
					loginbtn.setEnabled(true);
					lblError.setText(exception.getMessage());
				}finally {
					provider.CloseConnection();
				}
			}
		});
		loginbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginbtn.setCursor(provider.cursor);
		loginbtn.setForeground(new Color(255, 255, 255));
		loginbtn.setBorder(null);
		loginbtn.setBackground(new Color(51, 204, 204));
		loginbtn.setBounds(394, 270, 250, 23);
		panel.add(loginbtn);
		
		// label become partner
		lblPartner = new JLabel("Become Our Partner");
		lblPartner.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Register register=new Register("partner");
				register.setVisible(true);
				dispose();
			}
			
		});
		lblPartner.setCursor(provider.cursor);
		lblPartner.setForeground(new Color(102, 102, 102));
		lblPartner.setFont(new Font("Cambria", Font.BOLD, 13));
		lblPartner.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartner.setBounds(394, 375, 250, 14);
		panel.add(lblPartner);
		
		// label create account
		lblCreateAccount = new JLabel("New Here ? Create Account");
		lblCreateAccount.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Register register=new Register("customer");
				register.setVisible(true);
				dispose();
			}
		});
		lblCreateAccount.setCursor(provider.cursor);
		lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAccount.setForeground(new Color(0, 0, 204));
		lblCreateAccount.setFont(new Font("Cambria", Font.PLAIN, 13));
		lblCreateAccount.setBounds(394, 328, 250, 25);
		panel.add(lblCreateAccount);
		
		// label close
		lblClose = new JLabel("X");
		lblClose.setCursor(provider.cursor);
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
		
		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(394, 308, 250, 14);
		lblError.setFont(new Font("Cambria",Font.PLAIN,11));
		panel.add(lblError);
				
	}
}
