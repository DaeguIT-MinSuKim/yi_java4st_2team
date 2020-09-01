package rentcarTest.Dao.service;

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
	
	public long lookup(String kind) {
		return dao.rentLookupCarKind(kind);
	}
}
