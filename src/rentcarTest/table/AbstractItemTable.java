package rentcarTest.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;

@SuppressWarnings("serial")
public abstract class AbstractItemTable<T> extends JTable {
	public CustomModel model;
	private CustomerService service;
	private List<T> lists;

	public AbstractItemTable() {
		initComponents();
	}

	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	void loadData(List<T> itemList) {
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
	
	void tableCellAlign(int align, int...idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel tcm = getColumnModel(); // 각각의 column 가져옴
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	void tableSetWidth(int...width) {
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
		
		/*this.lists = itemList;
		System.out.println(lists);*/
		loadData(itemList);
		
		setWidthAndAlign();
		
	}
	
	public void addRow(Customer item) {
		this.service.insertCtm(item);
		//lists.add(item);
//		setItems();
	}

	public void removeRow(Customer item) {
		// TODO Auto-generated method stub
		
	}

	public void updateRow(Customer item) {
		// TODO Auto-generated method stub
		
	}

}
