package rentcarTest.Dao.service;

import java.util.Date;
import java.util.List;

import rentcarTest.Dao.RentDao;
import rentcarTest.Dao.Impl.RentDaoImpl;
import rentcarTest.dto.Rent;

public class RentService {
	private RentDao dao = RentDaoImpl.getInstance();
	
	public List<Rent> findRents(Rent item){
		return dao.selectRentByFind(item);
	}
	
	public List<Rent> showRents(){
		return dao.selectRentByAll();
	}
	
	public List<Rent> showFindRents(Rent rent, Date dateRent, Date dateReturn, String search){
		return dao.selectRentByAllFind(rent, dateRent, dateReturn, search);
	}
	
	public List<Rent> showRentsIng() {
		return dao.selectRentByRent();
	}
	
	public long lookup(String kind) {
		return dao.rentLookupCarKind(kind);
	}
}
