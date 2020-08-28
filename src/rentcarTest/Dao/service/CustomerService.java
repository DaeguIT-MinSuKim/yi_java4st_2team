package rentcarTest.Dao.service;

import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.Dao.Impl.CustomerDaoImpl;
import rentcarTest.dto.Customer;

public class CustomerService {
	private CustomerDao dao = CustomerDaoImpl.getInstance();
	
	public void insertCtm(Customer item) {
		dao.insertCustomer(item);
	}
	
	public List<Customer> showCustomers(){
		return dao.selectCustomerByAll();
	}
	
	public List<Customer> showRentCustomers(){
		return dao.selectCustomerByRent();
	}
	
	public void updateCtm(Customer item) {
		dao.updateCustomer(item);
	}
	
	public int lastCtmNo() {
		return dao.getLastCtm_no();
	}
}
