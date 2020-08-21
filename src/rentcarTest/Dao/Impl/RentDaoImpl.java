package rentcarTest.Dao.Impl;

import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.dto.Customer;

public class RentDaoImpl implements CustomerDao{

	@Override
	public List<Customer> selectCustomerByAll() {
		return null;
	}

	@Override
	public List<Customer> selectCustomerByFind(Customer ctm) {
		return null;
	}

	@Override
	public List<Customer> selectCustomerByRent(Customer ctm) {
		return null;
	}

	@Override
	public List<Customer> selectCustomerBlackList(Customer ctm) {
		return null;
	}

	@Override
	public int insertCustomer(Customer ctm) {
		return 0;
	}

	@Override
	public int updateCustomer(Customer ctm) {
		return 0;
	}

	@Override
	public int deleteCustomer(Customer ctm) {
		return 0;
	}

}
