package rentcarTest.Dao;

import java.util.List;

import rentcarTest.dto.Rent;

public interface RentDao {
	List<Rent> selectRentByAll();
	
	List<Rent> selectRentByFind(Rent rent);
	
	List<Rent> selectRentByDate(Rent rent);

	List<Rent> selectRentByRent();
	
	int insertRent (Rent rent);

	int updateRent (Rent rent);
	
	int rentLookupCarKind(String kind);
}
