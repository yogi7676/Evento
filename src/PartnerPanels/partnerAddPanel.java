package PartnerPanels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

import repository.provider;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.cj.protocol.Resultset;

import extras.LimitLength;
import model.collaboratorsModel;
import panels.commonViewPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class partnerAddPanel extends JPanel {
	provider provider = new provider();
	private JTextField comNameField, phoneField, comEmailField;
	private JTextArea textArea;
	private JLabel addImage;
	private JButton submitBtn;
	private List<String> selectedList = new ArrayList<String>();
	private JList list;

	public partnerAddPanel(String userId) {
		setBackground(Color.DARK_GRAY);
		setSize(994, 641);
		setLayout(null);

		JLabel iconEmail = new JLabel(provider.email);
		iconEmail.setBounds(73, 276, 30, 30);
		add(iconEmail);

		JLabel iconComName = new JLabel(provider.company);
		iconComName.setBounds(73, 51, 30, 30);
		add(iconComName);

		JLabel iconPhone = new JLabel(provider.whitePhone);
		iconPhone.setBounds(73, 232, 30, 30);
		add(iconPhone);

		JLabel iconAddress = new JLabel(provider.location);
		iconAddress.setBounds(73, 92, 30, 30);
		add(iconAddress);

		JLabel iconEvent = new JLabel(provider.event);
		iconEvent.setHorizontalAlignment(SwingConstants.CENTER);
		iconEvent.setBounds(73, 367, 30, 30);
		add(iconEvent);

		JLabel iconInfo = new JLabel(provider.info);
		iconInfo.setHorizontalAlignment(SwingConstants.CENTER);
		iconInfo.setBounds(73, 329, 30, 30);
		add(iconInfo);

		JLabel lblNewLabel_1 = new JLabel("Company Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 204, 153));
		lblNewLabel_1.setBounds(120, 51, 100, 30);
		add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Phone Number");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setForeground(new Color(255, 204, 153));
		lblNewLabel_3.setBounds(120, 232, 100, 30);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setForeground(new Color(255, 204, 153));
		lblNewLabel_4.setBounds(120, 92, 100, 30);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setForeground(new Color(255, 204, 153));
		lblNewLabel_5.setBounds(120, 276, 46, 30);
		add(lblNewLabel_5);

		JLabel lblNewLabel = new JLabel("Suitable for");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 204, 153));
		lblNewLabel.setBounds(120, 367, 110, 30);
		add(lblNewLabel);

		JLabel lblNewLabel_6 = new JLabel("Hold Ctrl to select multiple items.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_6.setForeground(new Color(51, 153, 153));
		lblNewLabel_6.setBounds(120, 329, 350, 30);
		add(lblNewLabel_6);

		comEmailField = new JTextField();
		comEmailField.setBounds(270, 273, 200, 33);
		add(comEmailField);
		comEmailField.setColumns(10);

		comNameField = new JTextField();
		comNameField.setBounds(270, 51, 200, 30);
		add(comNameField);
		comNameField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(270, 232, 200, 30);
		phoneField.setDocument(new LimitLength(10));
		add(phoneField);
		phoneField.setColumns(10);

		JScrollPane addressScroll = new JScrollPane();
		addressScroll.setBounds(270, 92, 200, 100);
		add(addressScroll);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setDocument(new LimitLength(300));
		addressScroll.setViewportView(textArea);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 365, 200, 100);
		add(scrollPane);

		list = new JList(provider.getEventsList().toArray());
		list.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList src = (JList) e.getSource();
				selectedList.clear();
				selectedList = (List<String>) src.getSelectedValuesList();
			}
		});
		scrollPane.setViewportView(list);

		addImage = new JLabel("Add Image");
		addImage.setCursor(provider.cursor);
		addImage.setFont(new Font("Tahoma", Font.BOLD, 12));
		addImage.setForeground(new Color(255, 204, 153));
		addImage.setBorder(new LineBorder(new Color(204, 204, 153), 1, true));
		addImage.setHorizontalAlignment(SwingConstants.CENTER);
		addImage.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				try {
					Image image = provider.pickImage();
					ImageIcon icon = new ImageIcon(image.getScaledInstance(414, 414, Image.SCALE_SMOOTH));
					addImage.setIcon(icon);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		addImage.setBounds(480, 51, 414, 414);
		add(addImage);

		submitBtn = new JButton("SUBMIT");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitBtn.setForeground(new Color(255, 255, 255));
		submitBtn.setCursor(provider.cursor);
		submitBtn.setBackground(new Color(51, 153, 153));
		submitBtn.setBorder(null);
		submitBtn.setBounds(340, 512, 300, 40);
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				validateInput(userId);
			}
		});
		add(submitBtn);
	}

	private void validateInput(String userId) {
		String cName, cPhone, cAddress, cEmail;
		cName = comNameField.getText();
		cPhone = phoneField.getText();
		cAddress = textArea.getText();
		cEmail = comEmailField.getText();

		try {
			if (cName.isEmpty() || cPhone.isEmpty() || cAddress.isEmpty() || cEmail.isEmpty()
					|| addImage.getIcon() == null || selectedList.size() == 0) {
				throw new Exception("Please fill in all the details.");
			} else if (provider.validatePhone(cPhone) == false) {
				throw new Exception("Enter valid phone number.");
			} else if (provider.validateEmail(cEmail) == false) {
				throw new Exception("Enter valid email address");
			} else {

				ResultSet set = provider.selectQuery(provider.getdBConnection(), "select * from collaborators");
				String col_id = new String();
				collaboratorsModel model;
				if (set.next()) {
					set = provider.selectQuery(provider.getdBConnection(),
							"SELECT col_id FROM collaborators ORDER BY joinedDate DESC LIMIT 1");
					if (set.next()) {
						col_id = provider.incrementId(set.getString(1));
					}
					add(col_id,cName,cAddress,cPhone,cEmail,userId);
				} else {
					add("C001",cName,cAddress,cPhone,cEmail,userId);
				}

				comNameField.setText("");
				phoneField.setText("");
				comEmailField.setText("");
				textArea.setText("");
				addImage.setIcon(null);
				list.clearSelection();
				String query = "select *from collaborators where userid='" + userId + "'";
				new commonViewPanel(query, "partner").populateView(0, query);
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(addImage, e.getMessage());
		} finally {
			provider.CloseConnection();
		}
	}

	private void add(String col_id,String cName, String cAddress, String cPhone, String cEmail, String userId)
			throws FileNotFoundException, ClassNotFoundException, SQLException {
		collaboratorsModel model = new collaboratorsModel(col_id, cName, cAddress, cPhone, cEmail,
				provider.getImagePath(), provider.getSelectedMap(selectedList), userId, provider.getCurrentDateTime());

		provider.addCompanyDetailsToDb(provider.getdBConnection(), model);
	}
}
