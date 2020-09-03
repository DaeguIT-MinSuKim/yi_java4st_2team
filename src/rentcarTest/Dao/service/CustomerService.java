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
	
	public List<Customer> findCustomers(Customer item){
		return dao.selectCustomerByFind(item);
	}
	
	public List<Customer> showRentCustomers(){
		return dao.selectCustomerByRent();
	}
	
	public List<Customer> showBlackListCustomers(){
		return dao.selectCustomerBlackList();
	}
	
	public List<Customer> selectCustomerToday(){
		return dao. selectCustomerToday();
	}
	
	public void updateCtm(Customer item) {
		dao.updateCustomer(item);
	}
	
	public int lastCtmNo() {
		return dao.getLastCtm_no();
	}
	
	public int deleteCtm(Customer item) {
		return dao.deleteCustomer(item);
	}
	
	public boolean deleteCheck(Customer ctm) {
		return dao.deleteCheck(ctm);
	}
}
