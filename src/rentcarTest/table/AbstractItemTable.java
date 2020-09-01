package rentcarTest.table;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;

@SuppressWarnings("serial")
public abstract class AbstractItemTable<T> extends JTable {
	public CustomModel model;
	private CustomerService service;
	protected List<T> lists;

	public AbstractItemTable() {
		initComponents();
	}

	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	void loadData(List<T> itemList) {
		this.lists = itemList;
		model = new CustomModel(getRows(itemList), getColName());
		setModel(model);
	}

	abstract Object[] getColName();

	private Object[][] getRows(List<T> itemList) {
		Object[][] rows = new Object[itemList.size()][];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = toArray(itemList.get(i));
		}
		return rows;
	}

	abstract Object[] toArray(T item);

	abstract void setWidthAndAlign();

	void tableCellAlign(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel tcm = getColumnModel(); // 각각의 column 가져옴
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	void tableSetWidth(int... width) {
		TableColumnModel tcm = getColumnModel(); // 각각의 column 가져옴
		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	private class CustomModel extends DefaultTableModel {

		public CustomModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false; // 각 셀 수정 불가능하게
		}

	}

	public void setService(CustomerService service) {
		this.service = service;
	}

	public void setItems(List<T> itemList) {
		loadData(itemList);

		setWidthAndAlign();

	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		T t = lists.get(row);

		if (t instanceof Customer) {
			Customer ctm = (Customer) t;
			if (ctm.getList_ctm() == 1) {
				c.setBackground(new Color(107, 107, 107));
				c.setForeground(new Color(255, 255, 255));
			} else {
				c.setBackground(new Color(255, 255, 255));
				c.setForeground(new Color(0, 0, 0));
			}
		}

		super.prepareRenderer(renderer, row, column);
		return c;
	}

	public void addRow(T item) {
		model.addRow(toArray(item));
	}

	public void removeRow(int idx) {
		model.removeRow(idx);
	}

	public void updateRow(int idx, T updateItem) {
		System.out.println("table");
		System.out.println(idx);
		System.out.println(updateItem);
		model.removeRow(idx);
		// model.up
		model.insertRow(idx, toArray(updateItem));
	}

}
