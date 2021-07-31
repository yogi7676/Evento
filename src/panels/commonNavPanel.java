package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import repository.provider;

public class commonNavPanel extends JPanel implements MouseListener {
	String userType;
	private provider provider = new provider();
	private JLabel homeBtn, usersBtn, addBtn, searchBtn, editBtn, myAccountBtn, contactBtn, feedBackBtn;
	private JPanel homePanel, usersPanel, addPanel, searchPanel, editPanel, myAccountPanel, contactPanel, feedBackPanel;

	public commonNavPanel(String userType) {
		this.userType = userType;
		initMenus();
	}

	public void navForAdmin(JPanel homePanel, JPanel usersPanel, JPanel searchPanel, JPanel myAccountPanel,
			JPanel feedbackPanel) {
		this.homePanel = homePanel;
		this.usersPanel = usersPanel;
		this.searchPanel = searchPanel;
		this.myAccountPanel = myAccountPanel;
		this.feedBackPanel = feedbackPanel;
	}

	public void navForCustomer(JPanel homePanel, JPanel searchPanel, JPanel myAccountPanel, JPanel contactPanel) {
		this.homePanel = homePanel;
		this.searchPanel = searchPanel;
		this.myAccountPanel = myAccountPanel;
		this.contactPanel = contactPanel;
	}

	public void navForPartner(JPanel homePanel, JPanel addPanel, JPanel editPanel, JPanel myAccountPanel,
			JPanel contactPanel) {
		this.homePanel = homePanel;
		this.addPanel = addPanel;
		this.editPanel = editPanel;
		this.myAccountPanel = myAccountPanel;
		this.contactPanel = contactPanel;
	}

	private void initMenus() {

		setBackground(new Color(0, 0, 0));
		setSize(100, 641);
		setLayout(null);
		// first menu
		homeBtn();

		// second menu
		switch (userType) {
		case "partner":
			addBtn();
			break;
		default:
			searchBtn();
		}

		// third menu
		switch (userType) {
		case "admin":
			usersBtn();
			break;
		case "customer":
			myAccountBtn();
			break;
		case "partner":
			editBtn();
			break;
		}

		// fourth menu
		switch (userType) {
		case "customer":
			contactBtn();
			break;
		default:
			myAccountBtn();
			break;
		}

		// fifth menu
		switch (userType) {
		case "partner":
			contactBtn();
			break;
		case "admin":
			feedbackBtn();
			break;
		}
	}

	private void feedbackBtn() {
		feedBackBtn = new JLabel("Feedbacks");
		feedBackBtn.setHorizontalAlignment(SwingConstants.CENTER);
		feedBackBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		feedBackBtn.setForeground(Color.white);
		feedBackBtn.setOpaque(true);
		feedBackBtn.setBackground(provider.menuUnSelectedColor);
		feedBackBtn.setCursor(provider.cursor);
		feedBackBtn.setFocusable(false);
		feedBackBtn.addMouseListener(this);
		feedBackBtn.setBorder(null);
		feedBackBtn.addMouseListener(null);
		feedBackBtn.setBounds(0, 330, 100, 30);
		add(feedBackBtn);
	}

	private void addBtn() {
		addBtn = new JLabel("Add");
		addBtn.setHorizontalAlignment(SwingConstants.CENTER);
		addBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		addBtn.setForeground(Color.white);
		addBtn.setOpaque(true);
		addBtn.setBackground(provider.menuUnSelectedColor);
		addBtn.setCursor(provider.cursor);
		addBtn.setFocusable(false);
		addBtn.addMouseListener(this);
		addBtn.setBorder(null);
		addBtn.addMouseListener(null);
		addBtn.setBounds(0, 210, 100, 30);
		add(addBtn);
	}

	private void editBtn() {
		editBtn = new JLabel("Edit");
		editBtn.setHorizontalAlignment(SwingConstants.CENTER);
		editBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		editBtn.setForeground(Color.white);
		editBtn.setBackground(provider.menuUnSelectedColor);
		editBtn.setBorder(null);
		editBtn.addMouseListener(this);
		editBtn.setCursor(provider.cursor);
		editBtn.setOpaque(true);
		editBtn.setFocusable(false);
		editBtn.setBounds(0, 250, 100, 30);
		add(editBtn);
	}

	private void homeBtn() {
		homeBtn = new JLabel("Home");
		homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		homeBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		homeBtn.setForeground(Color.white);
		homeBtn.setOpaque(true);
		homeBtn.setBackground(provider.menuSelectionColor);
		homeBtn.setCursor(provider.cursor);
		homeBtn.setFocusable(false);
		homeBtn.addMouseListener(this);
		homeBtn.setBorder(new MatteBorder(0, 3, 0, 0, (Color) new Color(255, 255, 255)));
		homeBtn.setBounds(0, 165, 100, 30);
		add(homeBtn);
	}

	private void usersBtn() {
		usersBtn = new JLabel("Users");
		usersBtn.setHorizontalAlignment(SwingConstants.CENTER);
		usersBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		usersBtn.setForeground(Color.white);
		usersBtn.setBackground(provider.menuUnSelectedColor);
		usersBtn.setBorder(null);
		usersBtn.setCursor(provider.cursor);
		usersBtn.addMouseListener(this);
		usersBtn.setOpaque(true);
		usersBtn.setFocusable(false);
		usersBtn.setBounds(0, 250, 100, 30);
		add(usersBtn);
	}

	private void myAccountBtn() {
		myAccountBtn = new JLabel("Account");
		switch (userType) {
		case "customer":
			myAccountBtn.setBounds(0, 250, 100, 30);
			break;
		default:
			myAccountBtn.setBounds(0, 290, 100, 30);
		}
		myAccountBtn.setFocusable(false);
		myAccountBtn.setOpaque(true);
		myAccountBtn.setBorder(null);
		myAccountBtn.setForeground(Color.white);
		myAccountBtn.setBackground(provider.menuUnSelectedColor);
		myAccountBtn.setHorizontalAlignment(SwingConstants.CENTER);
		myAccountBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		myAccountBtn.addMouseListener(this);
		myAccountBtn.setCursor(provider.cursor);
		add(myAccountBtn);
	}

	private void contactBtn() {
		contactBtn = new JLabel("Contact");
		contactBtn.setHorizontalAlignment(SwingConstants.CENTER);
		contactBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		contactBtn.setForeground(Color.white);
		contactBtn.setOpaque(true);
		contactBtn.setBackground(provider.menuUnSelectedColor);
		contactBtn.setCursor(provider.cursor);
		contactBtn.setFocusable(false);
		contactBtn.setBorder(null);
		contactBtn.addMouseListener(this);
		if (userType.equals("customer")) {
			contactBtn.setBounds(0, 290, 100, 30);
		} else if (userType.equals("partner")) {
			contactBtn.setBounds(0, 330, 100, 30);
		}
		add(contactBtn);
	}

	private void searchBtn() {
		searchBtn = new JLabel("Search");
		searchBtn.setHorizontalAlignment(SwingConstants.CENTER);
		searchBtn.setFont(new Font("Courier New", Font.BOLD, 12));
		searchBtn.setForeground(Color.white);
		searchBtn.setBackground(provider.menuUnSelectedColor);
		searchBtn.setBorder(null);
		searchBtn.setCursor(provider.cursor);
		searchBtn.setOpaque(true);
		searchBtn.setFocusable(false);
		searchBtn.setBounds(0, 210, 100, 30);
		searchBtn.addMouseListener(this);
		add(searchBtn);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == homeBtn)
			selection("home");
		else if (e.getSource() == addBtn)
			selection("add");
		else if (e.getSource() == editBtn)
			selection("edit");
		else if (e.getSource() == searchBtn)
			selection("search");
		else if (e.getSource() == usersBtn)
			selection("users");
		else if (e.getSource() == contactBtn)
			selection("contact");
		else if (e.getSource() == myAccountBtn)
			selection("acc");
		else if (e.getSource() == feedBackBtn)
			selection("feedback");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// method to select panels
	private void selection(String btn) {
		homeBtn.setBackground(btn.equals("home") ? provider.menuSelectionColor : provider.menuUnSelectedColor);
		homeBtn.setBorder(btn.equals("home") ? provider.selectionBorder : null);
		homePanel.setVisible(btn.equals("home") ? true : false);

		if (userType.equals("admin")) {
			usersBtn.setBackground(btn.equals("users") ? provider.menuSelectionColor : provider.menuUnSelectedColor);
			usersBtn.setBorder(btn.equals("users") ? provider.selectionBorder : null);
			usersPanel.setVisible(btn.equals("users") ? true : false);
		}

		myAccountPanel.setVisible(btn.equals("acc") ? true : false);
		myAccountBtn.setBorder(btn.equals("acc") ? provider.selectionBorder : null);
		myAccountBtn.setBackground(btn.equals("acc") ? provider.menuSelectionColor : provider.menuUnSelectedColor);

		if (userType.equals("admin") || userType.equals("customer")) {
			searchPanel.setVisible(btn.equals("search") ? true : false);
			searchBtn.setBorder(btn.equals("search") ? provider.selectionBorder : null);
			searchBtn.setBackground(btn.equals("search") ? provider.menuSelectionColor : provider.menuUnSelectedColor);
		}

		if (userType.equals("partner")) {
			addPanel.setVisible(btn.equals("add") ? true : false);
			addBtn.setBorder(btn.equals("add") ? provider.selectionBorder : null);
			addBtn.setBackground(btn.equals("add") ? provider.menuSelectionColor : provider.menuUnSelectedColor);

			editPanel.setVisible(btn.equals("edit") ? true : false);
			editBtn.setBorder(btn.equals("edit") ? provider.selectionBorder : null);
			editBtn.setBackground(btn.equals("edit") ? provider.menuSelectionColor : provider.menuUnSelectedColor);
		}

		if (userType.equals("partner") || userType.equals("customer")) {
			contactPanel.setVisible(btn.equals("contact") ? true : false);
			contactBtn.setBorder(btn.equals("contact") ? provider.selectionBorder : null);
			contactBtn
					.setBackground(btn.equals("contact") ? provider.menuSelectionColor : provider.menuUnSelectedColor);
		}

		if (userType.equals("admin")) {
			feedBackPanel.setVisible(btn.equals("feedback") ? true : false);
			feedBackBtn.setBorder(btn.equals("feedback") ? provider.selectionBorder : null);
			feedBackBtn
					.setBackground(btn.equals("feedback") ? provider.menuSelectionColor : provider.menuUnSelectedColor);
		}

	}

}
