package rentcarTest.daoImpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import rentcarTest.Dao.CarDao;
import rentcarTest.Dao.Impl.CarDaoImpl;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Kind;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarDaoImplTest {
	private CarDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = CarDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test04SelectCarByAll() {
		System.out.println("testSelectCarByAll");
		List<Car> list = dao.selectCarByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test04SelectCarByFind() {
		System.out.printf("%s()%n", "test04SelectCarByFind");
		Car car_find = new Car("38허4532");
		List<Car> car = dao.selectCarByFind(car_find);
		Assert.assertNotNull(car);
		car.stream().forEach(System.out::println);
		
		
		
	}

	@Test
	public void test05SelectCarByRent() {
		System.out.println("testSelectCarByRent");
		List <Car> list = dao. selectCarByRent();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test01InsertCar() {
		System.out.printf("%s()%n","testInsertCar");
		Car newCar = new Car("99구9999", "마티즈", new Kind("S"), "휘발유", 999, 35000, 5, "구구구");
		int res = dao.insertCar(newCar);
		Assert.assertEquals(1, res);
	}


	@Test
	public void test02UpdateCar() {
		Car updateCar = new Car("99구9999", "람보르기니", new Kind("M"), "휘발유", 999, 99999, 1, "구구구구구");
		int res = dao.updateCar(updateCar);
		System.out.printf("%s()%n","test02UpdateCustomer() : " + updateCar);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test03DeleteCar() {
		System.out.println("test03DeleteCustomer()");
		Car deleteCar = new Car("99구9999");
		int res = dao.deleteCar(deleteCar);
		Assert.assertEquals(1, res);
	}

}
