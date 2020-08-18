package rentcarTest.Dao;

import java.util.List;

import rentcarTest.dto.Car;

public interface CarDao {
	List<Car> selectCarByAll();
	
	List<Car> selectCarByFind(Car car);
	
	List<Car> selectCarByRent(Car car);

	int insertCar (Car car);

	int updateCar (Car car);
	
	int deleteCar (Car car);
}
