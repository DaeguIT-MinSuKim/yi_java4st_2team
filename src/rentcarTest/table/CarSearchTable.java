package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Car;

public class CarSearchTable extends AbstractItemTable<Car> {

	@Override
	Object[] getColName() {
		return new String[] {"차분류", "차종", "차번호", "연료", "기본요금", "장기할인"};
	}

	@Override
	Object[] toArray(Car item) {
		return new Object[] {
			item.getCarKind().getKind_name(),
			item.getCarName(),
			item.getCarNo(),
			item.getFuel(),
			item.getFare(),
			item.getSale(),
		};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 200, 100, 100, 100, 100);
		
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		
	}

}
