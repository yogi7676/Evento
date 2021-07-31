package Admin;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.EventObject;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import repository.provider;

public class feedbacks extends JPanel {
	private JTable Usertable;
	JScrollPane UserscrollPane;
	provider provider = new provider();

	public feedbacks() {
		setSize(994, 641);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		UserscrollPane = new JScrollPane();
		UserscrollPane.setBounds(20, 20, 927, 570);
		add(UserscrollPane);

		Usertable = new JTable() {
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		DefaultTableModel table_model = new DefaultTableModel(new String[] { "User ID", "FeedBack","Date of Feedback" }, 0);
		try {
			ResultSet set = provider.selectQuery(provider.getdBConnection(),
					"SELECT *FROM feedback ORDER BY dateOfFeedback DESC");
			while(set.next()) {
				table_model.addRow(new Object[] { set.getString(1), set.getString(2),set.getString(3) });
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(UserscrollPane, e.getMessage());
		} finally {
			provider.CloseConnection();
		}
		Usertable.setModel(table_model);
		Usertable.setRowSelectionAllowed(true);
		UserscrollPane.setViewportView(Usertable);
	}
}
