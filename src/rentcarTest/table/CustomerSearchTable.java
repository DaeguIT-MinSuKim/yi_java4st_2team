package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Customer;

@SuppressWarnings("serial")
public class CustomerSearchTable extends AbstractItemTable<Customer> {

	@Override
	Object[] getColName() {
		return new String[] { "고객번호", "성명", "연락처", "주소", "마일리지"};
	}

	@Override
	Object[] toArray(Customer item) {
		return new Object[] { String.format("%03d", item.getNo()), item.getName(), item.getTel(), item.getAddress(),
				String.format("%,d P", item.getMile())};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 200, 100, 100, 100);

		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);

	}
}
