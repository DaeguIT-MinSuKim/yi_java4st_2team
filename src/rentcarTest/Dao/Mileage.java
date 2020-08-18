package rentcarTest.Dao;

import java.util.List;

public interface Mileage {
	List<Mileage> selectRentByAll();
	
	List<Mileage> selectRentByName(Mileage mlg);
	
	int insertMileage (Mileage mlg);

	int updateMileage (Mileage mlg);
}
