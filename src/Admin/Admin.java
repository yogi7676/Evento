package Admin;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import repository.provider;

import panels.commonLeftPanel;
import panels.commonNavPanel;
import panels.commonSearchPanel;
import panels.commonTopPanel;
import panels.commonViewPanel;
import panels.commonAccountScreen;

public class Admin extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel homeBtn, usersBtn, myAccountBtn;
	commonViewPanel homePanel;
	adminUserPanel userPanel = new adminUserPanel();
	commonAccountScreen myAccountPanel;
	commonLeftPanel leftPanel = new commonLeftPanel("admin");
	commonTopPanel topPanel = new commonTopPanel("admin");
	commonSearchPanel searchPanel;
	feedbacks feedbackPanel;
	provider provider = new provider();

	public Admin(String userid) {
		getContentPane().setFont(new Font("Consolas", Font.PLAIN, 12));
		setBackground(Color.white);
		setSize(1300, 700);
		setTitle("Evento | Admin");
		setResizable(false);
		getContentPane().setLayout(null);

		initComponents(userid);
		setLocationRelativeTo(null);
	}

	private void initComponents(String userid) {
		try {
			getContentPane().add(leftPanel);
			getContentPane().add(topPanel);

			userPanel.setBounds(300, 30, 994, 641);
			userPanel.setVisible(false);
			getContentPane().add(userPanel);

			myAccountPanel = new commonAccountScreen(userid);
			myAccountPanel.setBounds(300, 30, 994, 641);
			myAccountPanel.setVisible(false);
			getContentPane().add(myAccountPanel);

			searchPanel = new commonSearchPanel("admin");
			searchPanel.setBounds(300, 30, 994, 641);
			getContentPane().add(searchPanel);
			searchPanel.setVisible(false);

			homePanel = new commonViewPanel("select * from collaborators", "admin");
			homePanel.setBounds(300, 30, 994, 641);
			getContentPane().add(homePanel);
			
			feedbackPanel=new feedbacks();
			feedbackPanel.setBounds(300, 30, 994, 641);
			getContentPane().add(feedbackPanel);
			feedbackPanel.setVisible(false);

			commonNavPanel nav = new commonNavPanel("admin");
			nav.navForAdmin(homePanel, userPanel, searchPanel, myAccountPanel,feedbackPanel);
			nav.setBounds(200, 30, 100, 641);
			getContentPane().add(nav);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
		} finally {
			provider.CloseConnection();
		}
	}
}