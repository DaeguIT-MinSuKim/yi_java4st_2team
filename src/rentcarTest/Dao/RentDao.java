package rentcarTest.Dao;

import java.util.List;

import rentcarTest.dto.Rent;

public interface RentDao {
	List<Rent> selectRentByAll();
	
	RentDao selectRentByFind(Rent rent);
	
	RentDao selectRentByDate(Rent rent);

	RentDao selectRentByRent(Rent rent);
	
	int insertRent (Rent rent);

	int updateRent (Rent rent);
}
