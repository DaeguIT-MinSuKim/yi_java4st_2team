package rentcarTest.table;

import javax.swing.SwingConstants;

import rentcarTest.dto.Rent;

public class RentTable extends AbstractItemTable<Rent>  {

	@Override
	Object[] getColName() {
		return new String[] {"차종", "차번호", "성명", "연락처", "기간", "운전기사", "비고"};
	}

	@Override
	Object[] toArray(Rent item) {
		return new Object[] {
			item.getCar_no().getCarName(),
			item.getCar_no().getCarNo(),
			item.getCtm_no().getName(),
			item.getCtm_no().getTel(),
			String.format("%s ~ %s", item.getRent_date(), item.getReturn_date()),
			item.getIs_driver(),
			item.getRent_remark()
		};
	}

	@Override
	void setWidthAndAlign() {
		tableSetWidth(100, 200, 100, 100, 100, 100, 100);
		
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		tableCellAlign(SwingConstants.LEADING, 6);
		
	}

}
