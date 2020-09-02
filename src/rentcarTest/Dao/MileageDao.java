package rentcarTest.Dao;

import java.util.List;

import rentcarTest.dto.Mileage;

public interface MileageDao {
	List<Mileage> selectMileageByAll();
		
	int insertMileage(Mileage mlg);

	List<Mileage> selectMileageByNo(Mileage mlg);

	int getLastMlg_no();

	/*
	 * int deleteMileage(Mileage deleteMlg);
	 */	}
