package rentcarTest;

import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.Dao.Impl.CustomerDaoImpl;
import rentcarTest.dto.Customer;

public class TestMain {
 
	public static void main(String[] args) {
		CustomerDao dao = CustomerDaoImpl.getInstance();
		List<Customer> list = dao.selectCustomerByAll();
		System.out.println(list);
	}

}
