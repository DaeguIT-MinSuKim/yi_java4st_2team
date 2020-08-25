package rentcarTest.Dao.service;

import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.Dao.Impl.CustomerDaoImpl;
import rentcarTest.dto.Customer;

public class CustomerService {
	private CustomerDao dao = CustomerDaoImpl.getInstance();
	
	public void joinCusotmer(Customer item) {
		dao.insertCustomer(item);
	}
	
	public List<Customer> showCustomers(){
		return dao.selectCustomerByAll();
	}
}
