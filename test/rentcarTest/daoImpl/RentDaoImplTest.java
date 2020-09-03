package rentcarTest.daoImpl;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import rentcarTest.Dao.Impl.RentDaoImpl;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Rent;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentDaoImplTest {
	
	@Test
	public void testSelectRentByAllRent() {
		System.out.println("testSelectRentByAllRent()");
		Rent rent = new Rent();
		Customer ctm_info = new Customer();
		Car car = new Car();
		car.setCarNo("29허9435");
		
		ctm_info.setName("동");
		ctm_info.setTel("7724");
		
		rent.setCar_no(car);
		rent.setCtm_no(ctm_info);		
		
		Calendar rent_date = Calendar.getInstance();
		rent_date.clear();
		rent_date.set(2015, 9, 27);
		
		Calendar return_date = Calendar.getInstance();
		return_date.clear();
		return_date.set(2020, 9, 27);
		
		//List<Rent> list = RentDaoImpl.getInstance().selectRentByAllFind(rent, rent_date.getTime(), return_date.getTime(), "성명");
		List<Rent> list = RentDaoImpl.getInstance().selectRentByAllFind(rent, null, null, null);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}
/*
	@Test
	public void test03SelectRentByAll() {
		System.out.println("testSelectRentByAll()");
		List<Rent> list = RentDaoImpl.getInstance().selectRentByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}

	@Test
	public void test04SelectRentByFind() {
		System.out.println("testSelectRentByFind()");
		Rent rent_search = new Rent(0);
		Customer ctm_info = new Customer();
		Car car = new Car();
		car.setCarNo("29허9435");
		
		rent_search.setCar_no(car);
		
		
		ctm_info.setName("홍길동");
		ctm_info.setTel("7724");
		
		rent_search.setCtm_no(ctm_info);		
		List<Rent> rent = RentDaoImpl.getInstance().selectRentByFind(rent_search);
		System.out.println(rent);
		Assert.assertNotNull(rent);
		rent.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectRentByDate() {
		System.out.println("test05SelectRentByDate()");
		Rent date_search = new Rent(0);
		
		Calendar rent_date = Calendar.getInstance();
		rent_date.clear();
		rent_date.set(2020, 4-1, 1, 12, 30, 50);
		
		Calendar return_date = Calendar.getInstance();
		return_date.clear();
		return_date.set(2020, 9, 27);
		
		date_search.setRent_date(rent_date.getTime());
		date_search.setReturn_date(return_date.getTime());
				
		List<Rent> rentList = RentDaoImpl.getInstance().selectRentByDate(date_search);
		Assert.assertNotNull(rentList);
		rentList.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectRentByRent() {
		System.out.println("testSelectRentByRent()");
		List <Rent> list = RentDaoImpl.getInstance().selectRentByRent();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	/*@Test
	public void test01InsertRent() {
		System.out.println("testInsertRent()");
		Rent newRent = new Rent();
		Customer ctm_info = new Customer();
		Car car_info = new Car();
		
		Calendar rent_date = Calendar.getInstance();
		rent_date.clear();
		rent_date.set(2020, 8-1, 25, 12, 00);

		
		Calendar return_date = Calendar.getInstance();
		return_date.clear();
		return_date.set(2020, 8-1, 27, 12, 00);

		ctm_info.setNo(6);
		ctm_info.setName("홍길동");
		ctm_info.setTel("010-1234-5678");
		
		car_info.setCarKind(new Kind("s", "소형"));
		car_info.setCarName("마티즈");
		car_info.setCarNo("12하4219");
		
		newRent.setRent_no(7);
		newRent.setCtm_no(ctm_info);
		newRent.setCar_no(car_info);		
		newRent.setRent_date(rent_date.getTime());
		newRent.setReturn_date(return_date.getTime());
		newRent.setRent_time(24);
		newRent.setIs_driver(1);
		newRent.setRent_remark("카시트 추가");
		
		
		System.out.println(newRent);
		int res = RentDaoImpl.getInstance().insertRent(newRent);
		Assert.assertEquals(1, res);
	}*

	@Test
	public void test02UpdateRent() {
		System.out.println("test02UpdateRent()");
		Rent updateRent = new Rent(7);
		
		Customer ctm_info = new Customer();
		Car car_info = new Car();
		
		Calendar rent_date = Calendar.getInstance();
		rent_date.clear();
		rent_date.set(2020, 8-1, 25);
		
		Calendar return_date = Calendar.getInstance();
		return_date.clear();
		return_date.set(2020, 8-1, 27);
		
		int dif_days = (int) ((return_date.getTimeInMillis() - rent_date.getTimeInMillis()) / (1000 * 60 * 60));
		
		ctm_info.setNo(6);
		ctm_info.setName("바꿀꺼야");
		ctm_info.setTel("010-1234-5678");
		
		car_info.setCarKind(new Kind("s", "소형"));
		car_info.setCarName("마티즈");
		car_info.setCarNo("12하4219");
		
		updateRent.setRent_no(7);
		updateRent.setCtm_no(ctm_info);
		updateRent.setCar_no(car_info);		
		updateRent.setRent_date(rent_date.getTime());
		updateRent.setReturn_date(return_date.getTime());
		updateRent.setRent_time(dif_days);
		updateRent.setIs_driver(1);
		updateRent.setRent_remark("카시트 추가");
		
		System.out.println(updateRent);
		int res = RentDaoImpl.getInstance().updateRent(updateRent);
		Assert.assertEquals(1, res);
	}
	*/

}
