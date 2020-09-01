package rentcarTest.Dao.service;

import java.util.List;

import rentcarTest.Dao.MileageDao;
import rentcarTest.Dao.Impl.MileageDaoImpl;
import rentcarTest.dto.Mileage;

public class MileageService {
	private MileageDao dao = MileageDaoImpl.getInstance();

	public void insertMile(Mileage mlg) {
		dao.insertMileage(mlg);
	}

	public List<Mileage> showMileage() {
		return dao.selectMileageByAll();
	}

	public int lastMlgNo() {
		return dao.getLastMlg_no();
	}

	public int deleteMlg(Mileage deleteMlg) {
		return dao.deleteMileage(deleteMlg);
	}
}
