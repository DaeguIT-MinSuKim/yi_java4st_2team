package rentcarTest.Dao;

import java.util.Date;
import java.util.List;

import rentcarTest.dto.Rent;

public interface RentDao {
	List<Rent> selectRentByAll();
	
	List<Rent> selectRentByAllFind(Rent rent, Date dateRent, Date dateReturn, String search);
	
	List<Rent> selectRentByFind(Rent rent);
	
	List<Rent> selectRentByDate(Rent rent);

	List<Rent> selectRentByRent();
	
	int insertRent (Rent rent);

	int updateRent (Rent rent);
	
	long rentLookupCarKind(String kind);
}
