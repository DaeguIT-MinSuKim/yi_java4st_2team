package rentcarTest.daoImpl;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.Dao.Impl.CustomerDaoImpl;
import rentcarTest.dto.Customer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoImplTest {
	private CustomerDao dao;

	@BeforeEach
	public void setUp() throws Exception {
		dao = CustomerDaoImpl.getInstance();
	}

	@AfterEach
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test04SelectCustomerByAll() {
		System.out.println("testSelectCustomerByAll");
		List<Customer> list = dao.selectCustomerByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test04SelectCustomerByFind() {
		fail("Not yet implemented");
	}

	@Test
	public void test06SelectCustomerByRent() {
		System.out.println("testSelectCustomerByRent");
		List <Customer> list = dao.selectCustomerByRent();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectCustomerBlackList() {
		fail("Not yet implemented");
	}

	@Test
	public void test01InsertCustomer() {
		System.out.printf("%s()%n","testInsertCustomer");
		Customer newCtm = new Customer(11, "백종원", "010","서울시 강남구","돈이 많다");
		int res = dao.insertCustomer(newCtm);
		Assert.assertEquals(1, res);
		}

	@Test
	public void test02UpdateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void test03DeleteCustomer() {
		System.out.println("testDeleteCustomer");
		Customer deleteCtm = new Customer(4);
		int res = dao.deleteCustomer(deleteCtm);
		Assert.assertEquals(1, res);
	}
}
