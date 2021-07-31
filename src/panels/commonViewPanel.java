package panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import model.collaboratorsModel;
import repository.provider;

public class commonViewPanel extends JPanel {

	provider provider = new provider();
	ArrayList<collaboratorsModel> list = new ArrayList<collaboratorsModel>();
	private Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	ResultSet set;
	int index = 0;
	JLabel prevBtn, nextBtn, imageLbl;
	private JLabel lblcompanyName, emailImage, phoneImagelbl, lblPhone, lblEmail, deleteLbl;
	String type;

	public commonViewPanel(String query, String type) {
		this.type = type;
		setBounds(0, 0, 994, 641);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		try {
			initComponents(query, type);
			populateView(index, query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void initComponents(String query, String type) {
		nextBtn = new JLabel(provider.next);
		nextBtn.setBounds(905, 275, 50, 50);
		nextBtn.setCursor(cursor);
		nextBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				try {
					if (index == list.size() - 1) {
						index = 0;
					} else {
						index += 1;
					}
					populateView(index, query);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(imageLbl, e2.getMessage());
				}
			}

		});

		if (type.equals("partner")) {
			deleteLbl = new JLabel(provider.delete);
			deleteLbl.setCursor(provider.cursor);
			deleteLbl.setHorizontalAlignment(SwingConstants.CENTER);
			deleteLbl.setBounds(935, 22, 20, 20);

			add(deleteLbl);
		}

		lblEmail = new JLabel();
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(66, 562, 391, 30);

		add(lblEmail);

		lblPhone = new JLabel("New label");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setForeground(new Color(255, 255, 255));
		lblPhone.setBounds(849, 562, 106, 30);

		add(lblPhone);

		phoneImagelbl = new JLabel(provider.coloredPhone);
		phoneImagelbl.setBounds(809, 562, 30, 30);
		add(phoneImagelbl);

		emailImage = new JLabel(provider.coloredEmail);
		emailImage.setBounds(22, 562, 30, 30);
		add(emailImage);
		add(nextBtn);

		prevBtn = new JLabel(provider.prev);
		prevBtn.setBounds(22, 275, 50, 50);
		prevBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				try {
					if (index != 0)
						index -= 1;
					else
						index = list.size() - 1;
					populateView(index, query);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(imageLbl, e2.getMessage());
				}
			}

		});
		prevBtn.setCursor(cursor);
		add(prevBtn);

		lblcompanyName = new JLabel();
		lblcompanyName.setForeground(new Color(255, 255, 255));
		lblcompanyName.setFont(new Font("Courier New", Font.BOLD, 18));
		lblcompanyName.setBounds(22, 22, 510, 31);
		add(lblcompanyName);

		imageLbl = new JLabel();
		imageLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		imageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		imageLbl.setBounds(10, 11, 960, 593);

		add(imageLbl);
	}

	public void populateView(int selectedIndex, String query)
			throws JsonMappingException, JsonParseException, ClassNotFoundException, SQLException, IOException {
		list = provider.bindTable(provider.getdBConnection(), query);
		if (list.size() != 0) {
			setVisibility(true);
			ImageIcon image = new ImageIcon(new ImageIcon(list.get(selectedIndex).getImage()).getImage()
					.getScaledInstance(960, 593, Image.SCALE_SMOOTH));
			imageLbl.setIcon(image);
			lblcompanyName.setText(list.get(selectedIndex).getCompanyId() + " : "
					+ list.get(selectedIndex).getCompanyName().toUpperCase());
			lblEmail.setText(list.get(selectedIndex).getEmail().toLowerCase());
			lblPhone.setText(list.get(selectedIndex).getPhone());
		} else {
			setVisibility(false);
		}

	}

	private void setVisibility(Boolean visible) {
		if (type.equals("partner"))
			deleteLbl.setVisible(visible);
		lblEmail.setVisible(visible);
		imageLbl.setVisible(visible);
		lblcompanyName.setVisible(visible);
		prevBtn.setVisible(visible);
		nextBtn.setVisible(visible);
		emailImage.setVisible(visible);
		phoneImagelbl.setVisible(visible);
		lblPhone.setVisible(visible);
	}
}
