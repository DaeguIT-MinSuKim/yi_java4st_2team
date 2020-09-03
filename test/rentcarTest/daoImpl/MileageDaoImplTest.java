package rentcarTest.daoImpl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import rentcarTest.Dao.MileageDao;
import rentcarTest.Dao.Impl.MileageDaoImpl;
import rentcarTest.dto.Mileage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MileageDaoImplTest {
	private MileageDao dao;

	@Before
	public void setUp() throws Exception {
		dao = MileageDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test04SelectMileageByAll() {
		System.out.println("testSelectMileageByAll()");
		List<Mileage> list = dao.selectMileageByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
		
	@Test
	public void test03SelectMileageByNo() {
		System.out.println("test03SelectRentByNo()");
		Mileage mile_search = new Mileage(0);
		mile_search.setMlg_no(2);
		List<Mileage> list = dao.selectMileageByNo(mile_search);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test01InsertMileageDao() {
		System.out.printf("%s()%n", "testInsertMileageDao()");
		Mileage newMile = new Mileage(10, 8, 1, 8000, "첫 가입");
		int res = dao.insertMileage(newMile);
		Assert.assertEquals(1, res);
	}
}
