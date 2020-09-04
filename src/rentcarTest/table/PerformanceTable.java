package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Rent;
import rentcarTest.dto.RentPerformance;

public class PerformanceTable extends AbstractItemTable<RentPerformance> {

	@Override
	Object[] getColName() {
		return new String[] { "차분류", "차종", "대여시간", "할인요금", "최종요금" };
	}

	@Override
	Object[] toArray(RentPerformance item) {
		
		return new Object[] {
				item.getCar_Kind(),
				item.getCar_Name(),
				item.getRent_Time(),
				item.getSale_Price(),
				item.getFinal_Price()
		};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 100, 100, 100, 100);

		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);
	}
}
