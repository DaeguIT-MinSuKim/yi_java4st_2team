package rentcarTest.table;

import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import rentcarTest.dto.Customer;
import rentcarTest.dto.Rent;

@SuppressWarnings("serial")
public class CustomerRentTable extends AbstractItemTable<Rent> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	@Override
	Object[] getColName() {
		return new String[] { "차분류", "차종", "차번호", "대여일자", "반납일자", "운전기사" };
	}

	@Override
	Object[] toArray(Rent item) {
		return new Object[] { item.getCar_no().getCarKind().getKind_name(),
				item.getCar_no().getCarName(),
				item.getCar_no().getCarNo(),
				df.format(item.getRent_date()),
				df.format(item.getReturn_date()),
				item.getSIs_driver()
		};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 100, 100, 200, 200, 100);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
	}
}
