package rentcarTest.Dao;

import java.util.List;

public interface MileageDao {
	List<MileageDao> selectRentByAll();
	
	List<MileageDao> selectRentByName(MileageDao mlg);
	
	int insertMileageDao (MileageDao mlg);
}
