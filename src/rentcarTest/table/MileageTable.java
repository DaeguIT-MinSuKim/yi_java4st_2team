package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Mileage;

public class MileageTable extends AbstractItemTable<Mileage> {
	@Override
	Object[] getColName() {
		return new String[] { "마일리지번호", "고객번호", "추가/차감", "마일리지", "비고" };
	}

	@Override
	Object[] toArray(Mileage item) {
		return new Object[] {
			item.getMlg_no(),
			item.getCtm_no(),
			item.getMlg_kind(), 
			item.getPoint(),
			item.getMlg_remark()
		};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 200, 100, 100, 100);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);

	}
}