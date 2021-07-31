package panels;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import repository.provider;

public class commonAccountScreen extends JPanel {
	provider provider = new provider();

	public commonAccountScreen(String user_id) {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
			
		try {
			initComponents(user_id);
			populate(user_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void initComponents(String user_id) {
		JLabel lblNewLabel = new JLabel(provider.account);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(447, 85, 100, 100);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(provider.userIcon);
		lblNewLabel_1.setBounds(277, 205, 30, 30);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(provider.email);
		lblNewLabel_2.setBounds(277, 259, 30, 30);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(provider.password);
		lblNewLabel_3.setBounds(277, 316, 30, 30);
		add(lblNewLabel_3);
		
		JLabel editPass = new JLabel(provider.edit);
		editPass.setBounds(693, 316, 30, 30);
		editPass.setCursor(provider.cursor);
		editPass.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				JDialog dialog=new JDialog();
				dialog.setSize(400,250);
				dialog.setTitle("Evento | Change Password");
				dialog.add(new changePasswordPanel(user_id));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
			
		});
		add(editPass);
	
		JLabel lblNewLabel_6 = new JLabel("*************");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(363, 316, 308, 30);
		add(lblNewLabel_6);
	}
	
	public void populate(String user_id) {
		
		try {
			ResultSet set = provider.selectQuery(provider.getdBConnection(),
					"SELECT u.login_id,u.username,l.email FROM users u,login l WHERE user_id=" + "'" + user_id
							+ "'" + " AND l.login_id=u.login_id");
			if(set.next()) {
				
				
				JLabel lblNewLabel_4 = new JLabel(set.getString(2));
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_4.setForeground(Color.WHITE);
				lblNewLabel_4.setBounds(363, 205, 308, 30);
				add(lblNewLabel_4);
				
				JLabel lblNewLabel_5 = new JLabel(set.getString(3));
				lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_5.setForeground(Color.WHITE);
				lblNewLabel_5.setBounds(364, 259, 307, 30);
				add(lblNewLabel_5);
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e.getMessage());
		}finally {
			provider.CloseConnection();
		}
	}
}
