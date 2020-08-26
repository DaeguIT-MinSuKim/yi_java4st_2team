package rentcarTest.daoImpl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import rentcarTest.Dao.Impl.RentDaoImpl;
import rentcarTest.dto.Rent;

public class RentDaoImplTest {

	@Test
	public void testSelectRentByAll() {
		System.out.println("testSelectRentByAll()");
		List<Rent> list = RentDaoImpl.getInstance().selectRentByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}

	/*@Test
	public void testSelectRentByFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectRentByDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectRentByRent() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertRent() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRent() {
		fail("Not yet implemented");
	}*/

}
