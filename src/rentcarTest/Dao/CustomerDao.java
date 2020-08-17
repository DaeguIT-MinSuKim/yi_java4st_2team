package rentcarTest.Dao;

import java.util.List;

import rentcarTest.dto.Customer;

public interface CustomerDao {
	List<Customer> selectCustomerByAll();
}
