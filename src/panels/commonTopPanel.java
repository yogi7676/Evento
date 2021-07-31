package panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import repository.provider;

public class commonTopPanel extends JPanel {
	JButton logoutBtn;
	private Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	provider provider=new provider();
	
	public commonTopPanel(String usertype) {
		setBounds(200, 0, 1094, 30);
		setLayout(null);

		logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		logoutBtn.setBorder(null);
		logoutBtn.setForeground(Color.white);
		logoutBtn.setCursor(cursor);
		logoutBtn.setFocusable(false);
		logoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				provider.logout();
			}
		});
		logoutBtn.setBounds(1005, 0, 89, 30);
		add(logoutBtn);
		
		switch(usertype) {
		case "admin":
			setBackground(provider.adminColor);
			logoutBtn.setBackground(provider.adminColor);
			break;
		case "partner":
			setBackground(provider.partnerColor);
			logoutBtn.setBackground(provider.partnerColor);
			break;
		case "customer":
			setBackground(provider.userColor);
			logoutBtn.setBackground(provider.userColor);
			break;
		}
		
	}

}
