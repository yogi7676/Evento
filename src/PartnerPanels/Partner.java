package PartnerPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import panels.commonContactScreen;
import panels.commonLeftPanel;
import panels.commonNavPanel;
import panels.commonTopPanel;
import panels.commonViewPanel;
import panels.contact;
import panels.commonAccountScreen;
import repository.provider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Partner extends JFrame {
	provider provider = new provider();
	commonLeftPanel commonLeftPanel = new commonLeftPanel("partner");
	commonTopPanel commonTopPanel = new commonTopPanel("partner");
	JLabel homeBtn, myAccountBtn, editBtn, addBtn, contactBtn;
	commonAccountScreen myAccountPanel;
	partnerEditPanel editPanel;
	commonViewPanel homePanel;
	partnerAddPanel addPanel;
	commonContactScreen contactPanel;

	public Partner(String userId) {
		setSize(1300, 700);
		setResizable(false);
		setTitle("Evento");
		setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		try {
			getContentPane().add(commonLeftPanel);
			getContentPane().add(commonTopPanel);

			// common view Panel
			homePanel = new commonViewPanel("select *from collaborators where userid='" + userId + "'", "partner");
			homePanel.setBounds(300, 30, 994, 641);
			getContentPane().add(homePanel);

			// partner add panel
			addPanel = new partnerAddPanel(userId);
			addPanel.setBounds(300, 30, 994, 641);
			addPanel.setVisible(false);
			getContentPane().add(addPanel);

			// partner edit panel
			editPanel = new partnerEditPanel(userId);
			editPanel.setBounds(300, 30, 994, 641);
			editPanel.setVisible(false);
			getContentPane().add(editPanel);

			// my Account Panel
			myAccountPanel = new commonAccountScreen(userId);
			myAccountPanel.setBounds(300, 30, 994, 641);
			myAccountPanel.setVisible(false);
			getContentPane().add(myAccountPanel);

			// contact Panel
			contactPanel = new commonContactScreen(userId);
			contactPanel.setBounds(300, 30, 994, 641);
			contactPanel.setVisible(false);
			getContentPane().add(contactPanel);

			commonNavPanel nav = new commonNavPanel("partner");
			nav.setBounds(200, 30, 100, 641);
			nav.navForPartner(homePanel, addPanel, editPanel, myAccountPanel, contactPanel);
			getContentPane().add(nav);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}