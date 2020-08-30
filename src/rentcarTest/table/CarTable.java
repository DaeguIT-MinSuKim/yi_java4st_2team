package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Car;

public class CarTable extends AbstractItemTable<Car> {

	@Override
	Object[] getColName() {
		return new String[] {"차분류", "차종", "차번호", "연료", "이동거리", "기본요금", "장기할인", "비고"};
	}

	@Override
	Object[] toArray(Car item) {
		return new Object[] {
			item.getCarKind().getKind_name(),
			item.getCarName(),
			item.getCarNo(),
			item.getFuel(),
			item.getDistance(),
			item.getFare(),
			item.getSale(),
			item.getCarRemark(),
		};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 200, 100, 100, 100, 100, 100, 500);
		
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
		tableCellAlign(SwingConstants.LEADING, 7);
		
	}

}
