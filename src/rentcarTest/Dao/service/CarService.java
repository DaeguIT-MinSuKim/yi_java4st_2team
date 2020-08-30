package rentcarTest.Dao.service;

import java.util.List;

import rentcarTest.Dao.CarDao;
import rentcarTest.Dao.Impl.CarDaoImpl;
import rentcarTest.dto.Car;

public class CarService {
	private CarDao dao = CarDaoImpl.getInstance();
	
	public List<Car> showCars(){
		return dao.selectCarByAll();
	}
	
	public List<Car> showRentCars(){
		return dao.selectCarByRent();
	}
	
	public List<Car> findCars(Car item){
		return dao.selectCarByFind(item);
	}

	public int deleteCar(Car item) {
		return dao.deleteCar(item);
		
	}
	
	public int insertCar(Car item) {
		return dao.insertCar(item);
	}

}
