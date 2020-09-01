package rentcarTest.Dao;

import java.util.List;

import rentcarTest.dto.Customer;

public interface CustomerDao {
	List<Customer> selectCustomerByAll();
	
	List<Customer> selectCustomerByFind(Customer ctm);
	
	List<Customer> selectCustomerByRent();

	List<Customer> selectCustomerBlackList();
	
	int insertCustomer (Customer ctm);

	int updateCustomer (Customer ctm);
	
	int deleteCustomer (Customer ctm);
	
	int getLastCtm_no();

	List<Customer> selectCustomerToday();
}
