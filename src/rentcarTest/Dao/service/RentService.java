package rentcarTest.Dao.service;

import rentcarTest.Dao.RentDao;
import rentcarTest.Dao.Impl.RentDaoImpl;

public class RentService {
	private static RentDao dao = RentDaoImpl.getInstance();
	
	public int lookup(String kind) {
		return dao.rentLookupCarKind(kind);
	}
	
}
