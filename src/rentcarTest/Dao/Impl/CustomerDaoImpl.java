package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Customer;

public class CustomerDaoImpl implements CustomerDao {
	private static final CustomerDaoImpl instance = new CustomerDaoImpl();

	private CustomerDaoImpl() {
	}

	public static CustomerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Customer> selectCustomerByAll() {
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK FROM CUSTOMER ORDER BY CTM_NO";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Customer> list = new ArrayList<Customer>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Customer> selectCustomerByFind(Customer ctm) {
		return null;
	}

	@Override
	public List<Customer> selectCustomerByRent(Customer ctm) {
		String sql = "SELECT C.CAR_KIND, CT.CTM_NAME, CT.TEL, CT.ADDRESS, M.MILEAGE, C.IS_RENT, CT.CTM_REMARK"
				+ "FROM RENT" + "	LEFT OUTER JOIN CUSTOMER CT ON CT.CTM_NO = R.CTM_NO"
				+ "	LEFT OUTER JOIN CAR C ON C.CAR_NO = R.CAR_NO"
				+ "	LEFT OUTER JOIN MILEAGE M ON M.CTM_NO = R.CTM_NO";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, ctm.getNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Customer> list = new ArrayList<Customer>();
					do {
						list.add(getCustomer(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
		String sql = "DELETE FROM CUSTOMER WHERE CTM_NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, ctm.getNo());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Customer getCustomer(ResultSet rs) throws SQLException {
		int no = rs.getInt("CTM_NO");
		String name = rs.getString("CTM_NAME");
		String tel = rs.getString("TEL");
		String address = rs.getString("ADDRESS");
		String remark = rs.getString("CTM_REMARK");
		return new Customer(no, name, tel, address, remark);
	}

}
