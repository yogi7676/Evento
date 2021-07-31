package panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import repository.provider;

public class commonLeftPanel extends JPanel {
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	provider provider=new provider();
	
	public commonLeftPanel(String userType) {
		setBounds(0, 0, 200, 671);
		setLayout(null);

		lblNewLabel = new JLabel("EVENTO");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 30, 180, 37);
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel("We Manage, You Celebrate");
		lblNewLabel_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 60, 180, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Copyrights @ Evento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 640, 180, 14);
		add(lblNewLabel_2);
		
		switch(userType) {
		case "admin":
			setBackground(provider.adminColor);
			break;
		case "partner":
			setBackground(provider.partnerColor);
			break;
		case "customer":
			setBackground(provider.userColor);
			break;
		}
	}

}
