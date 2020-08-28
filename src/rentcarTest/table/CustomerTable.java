package rentcarTest.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import rentcarTest.dto.Customer;

@SuppressWarnings("serial")
public class CustomerTable extends AbstractItemTable<Customer> {

	@Override
	Object[] getColName() {
		return new String[] {"고객번호", "성명", "연락처", "주소", "마일리지", "비고"};
	}

	@Override
	Object[] toArray(Customer item) {
		return new Object[] {
			String.format("%03d", item.getNo()),
			item.getName(),
			item.getTel(),
			item.getAddress(),
			String.format("%,d P", item.getCtm_mlg()),
			item.getRemark()};
	}
	

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 200, 100, 100, 100, 100);
		
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		
	}
}
