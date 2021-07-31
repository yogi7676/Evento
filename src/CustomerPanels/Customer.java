package CustomerPanels;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panels.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import repository.provider;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class Customer extends JFrame {
	provider provider = new provider();
	commonLeftPanel leftPanel = new commonLeftPanel("customer");
	commonTopPanel topPanel = new commonTopPanel("customer");
	JLabel homeBtn, searchBtn, myAccountBtn, contactBtn;
	commonAccountScreen myAccountPanel;
	customerViewPanel homePanel;
	commonContactScreen contactPanel;
	commonSearchPanel searchPanel;

	public Customer(String userId) {
		setSize(1300, 700);
		setTitle("Evento");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		commonNavPanel nav = new commonNavPanel("customer");

		try {
			getContentPane().add(leftPanel);
			getContentPane().add(topPanel);

			homePanel = new customerViewPanel();
			homePanel.setBounds(300, 30, 994, 641);
			getContentPane().add(homePanel);
			searchPanel = new commonSearchPanel("customer");
			searchPanel.setBounds(300, 30, 994, 641);
			getContentPane().add(searchPanel);
			searchPanel.setVisible(false);

			myAccountPanel = new commonAccountScreen(userId);
			myAccountPanel.setBounds(300, 30, 994, 641);
			getContentPane().add(myAccountPanel);
			myAccountPanel.setVisible(false);

			contactPanel = new commonContactScreen(userId);
			contactPanel.setBounds(300, 30, 994, 641);
			getContentPane().add(contactPanel);
			contactPanel.setVisible(false);

			nav.navForCustomer(homePanel, searchPanel, myAccountPanel, contactPanel);
			nav.setBounds(200, 30, 100, 641);
			getContentPane().add(nav);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}