package rentcarTest.daoImpl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.Dao.Impl.CustomerDaoImpl;
import rentcarTest.dto.Customer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoImplTest {
	private CustomerDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = CustomerDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test05SelectCustomerByAll() {
		System.out.println("testSelectCustomerByAll");
		List<Customer> list = dao.selectCustomerByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test04SelectCustomerByFind() {
		System.out.printf("%s()%n", "test04SelectCustomerByFind");
	      Customer ctm_search = new Customer(1);
	      ctm_search.setNo(0);
	      ctm_search.setName("홍길동");
	      ctm_search.setTel("010-5177-0965");
	      ctm_search.setAddress("지구");
	      
	      List<Customer> ctm = dao.selectCustomerByFind(ctm_search);
	      Assert.assertNotNull(ctm);
	      ctm.stream().forEach(System.out::println);
}


	@Test
	public void test06SelectCustomerByRent() {
		System.out.println("testSelectCustomerByRent");
		List <Customer> list = dao.selectCustomerByRent();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test07SelectCustomerBlackList() {
		System.out.printf("%s()%n","SelectCustomerBlackList()");
		List <Customer> list = dao.selectCustomerBlackList();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test01InsertCustomer() {
		System.out.printf("%s()%n","testInsertCustomer");
		Customer newCtm = new Customer(12, "백종원", "010","서울시 강남구","돈이 많다", 0);
		int res = dao.insertCustomer(newCtm);
		Assert.assertEquals(1, res);
		}

	@Test
	public void test02UpdateCustomer() {
		System.out.printf("%s()%n","test02UpdateCustomer()");
		Customer updateCtm = new Customer(2, "김창동", "010-7724-6072", "대구 두류동", "근육천사", 99999);
		int res = dao.updateCustomer(updateCtm);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test03DeleteCustomer() {
		System.out.println("test03DeleteCustomer()");
		Customer deleteCtm = new Customer(12);
		int res = dao.deleteCustomer(deleteCtm);
		Assert.assertEquals(1, res);
	}
}
