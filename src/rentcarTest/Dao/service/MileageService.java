package rentcarTest.Dao.service;

import java.util.List;

import rentcarTest.Dao.MileageDao;
import rentcarTest.Dao.Impl.MileageDaoImpl;
import rentcarTest.dto.Mileage;

public class MileageService {
	private MileageDao dao = MileageDaoImpl.getInstance();

	public void insertMile(Mileage mlg) {
		System.out.println("Inserting Mileage..");
		dao.insertMileage(mlg);
	}

	public List<Mileage> showMileage() {
		return dao.selectMileageByAll();
	}
}
