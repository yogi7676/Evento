package extras;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class TheTableModel extends AbstractTableModel {

	private String[] columns;
	private Object[][] rows;

	public TheTableModel(Object[][] rows, String[] columns) {
		super();
		this.columns = columns;
		this.rows = rows;
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 5) {
			return Icon.class;
		} else {
			return getValueAt(0, columnIndex).getClass();
		}

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.rows[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.columns[column];
	}

}
