package panels;

import java.awt.*;
import javax.swing.*;

import extras.LimitLength;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

public class contact extends JPanel {
	
	LimitLength limLength=new LimitLength();
	
	public contact(String userId) {
		setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(153, 102, 153)));
		setBackground(Color.white);
		setLayout(null);
		
		JTextArea area = new JTextArea();
		area.setFont(new Font("Cambria", Font.PLAIN, 12));
		area.setBounds(10, 193, 246, 200);
		area.setLineWrap(true);
		area.setBackground(new Color(255, 255, 255));
		area.setDocument(new LimitLength(500));
		JScrollPane pane = new JScrollPane(area);
		pane.setBorder(new LineBorder(new Color(204, 153, 153), 3, true));
		pane.setBounds(10, 47, 230, 131);
		
		add(pane);
		
		JLabel lblNewLabel = new JLabel("Write Your Queries.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 22, 230, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Character length must be less than 500.</html>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(255, 102, 102));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 189, 230, 22);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(204, 153, 102));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(10, 215, 230, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("For Further Information");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 264, 230, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contact Us : 9876543210");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 289, 230, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mail us : evento@bangalore.com");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 311, 230, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Visit Us\r\n");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 338, 230, 14);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("<html>#11/3,8th Block, 5th Main, 7th Cross, Jayanagar, Bangalore, Karnataka.</html>");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 363, 230, 48);
		add(lblNewLabel_6);
	}
}
