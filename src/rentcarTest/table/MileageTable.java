package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Mileage;

public class MileageTable extends AbstractItemTable<Mileage> {
	@Override
	Object[] getColName() {
		return new String[] { "마일리지번호", "고객번호", "마일리지 타입", "마일리지", "비고" };
	}

	@Override
	Object[] toArray(Mileage item) {
		return new Object[] { item.getMlg_no(), item.getCtm_no(), item.getMlg_kind() , item.getPoint(),
				item.getMlg_remark() };
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(50,50, 100, 100, 100);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);

	}
}